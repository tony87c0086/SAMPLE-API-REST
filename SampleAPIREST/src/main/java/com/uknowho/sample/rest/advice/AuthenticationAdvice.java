package com.uknowho.sample.rest.advice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uknowho.sample.rest.security.CustomRole;
import com.uknowho.sample.rest.security.CustomUserDetails;
import com.uknowho.sample.rest.task.APITimerTask;
import com.uknowho.sample.rest.utility.DataFormat;
import com.uknowho.sample.rest.utility.DateAdapter;

/**
 * This AuthenticationAdvice class is define customized authentication security initial set ups. 
 * 
 * Created date <12-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Component
public class AuthenticationAdvice {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationAdvice.class);
	
	public static Map<String, CustomUserDetails> userDetailMap = null;
	public static APITimerTask timerTask = null;
	
	private static enum APIUser {
		USERNAME (100, "sample"),
		PASSWORD (200, "password"),
		ROLENAME (300, "ROLE_ADMIN"),
		HOURRATE (500, "500"),
		HOURATTEMPT (40, "40"),
		DOMAINRANGE (600, "*"),
		ALLOWMETHOD (700, "GET,POST,PUT,PATCH,DELETE,OPTIONS");
		
		private final int id;
		private final String value;
		APIUser (int id, String value) {
			this.id = id;
			this.value = value;
		}
	}
	
	public AuthenticationAdvice() {
		userDetailMap = new HashMap<String, CustomUserDetails>();
		timerTask = new APITimerTask();
		
		initCustomUserDetail();
		timerTask.getTimer().schedule(timerTask.hourlyTask, 0l, 1000*60*60); // 
	}
	
	private void initCustomUserDetail() {
		try {
			CustomUserDetails customUserDetail = new CustomUserDetails();
			CustomRole customRole = new CustomRole();
			
			// Only for one authorized user for now
			customUserDetail.setUsername(APIUser.USERNAME.value);
			customUserDetail.setPassword(APIUser.PASSWORD.value);
			customUserDetail.setMaxHourRate(APIUser.HOURRATE.id);
		//	customUserDetail.setActiveRate(APIUser.HOURRATE.id);
			customUserDetail.setMaxHourAttempt(APIUser.HOURATTEMPT.id);
		//	customUserDetail.setActiveAttempt(APIUser.HOURATTEMPT.id);
			customUserDetail.setAllowMethod(APIUser.ALLOWMETHOD.value);
			customUserDetail.setDomainRange(APIUser.DOMAINRANGE.value);
			
			customRole.setRoleName(APIUser.ROLENAME.value);
			customUserDetail.getAuthorities().add(customRole);
			
			customUserDetail.setLastActiveTime(DateAdapter.getDateTime());
			
			// userDetailMap store username / customUserDetail pair values
			
			String userName = customUserDetail.getUsername();
			userDetailMap.put(userName, customUserDetail);
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	public static Map<String, CustomUserDetails> getCustomUserMap() {
		return userDetailMap;
	}
	
	public static CustomUserDetails getCustomUserDetails(final String userName) {
		CustomUserDetails customUserDetail = null;
		try {
			if ((userDetailMap != null) &&!(userDetailMap.isEmpty())) {
				String key = null;
				
				for (Map.Entry<String, CustomUserDetails> entry : userDetailMap.entrySet()) {
					key = (String) entry.getKey();
					
					if (DataFormat.isStringEqualSensitive(key, userName)) {
						customUserDetail = entry.getValue();
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return customUserDetail;
	}
	
	public static CustomUserDetails updateAuthenticationRate(final String userName) {
		return updateAuthenticationInfo(userName, true, false);
	}
	
	public static CustomUserDetails updateAuthenticationAttempt(final String userName) {
		return updateAuthenticationInfo(userName, false, true);
	}
	
	private static CustomUserDetails updateAuthenticationInfo(final String userName, 
			final boolean isRate,
			final boolean isAttempt) {
		CustomUserDetails userDetail = null;
		try {
			Iterator<Map.Entry<String,CustomUserDetails>> iter = null;
			iter = AuthenticationAdvice.getCustomUserMap().entrySet().iterator();
			
			Integer activeRate = 0;
			Integer activeAttempt = 0;
			while (iter.hasNext()) {
			    Map.Entry<String,CustomUserDetails> entry = iter.next();
			   
			    if(DataFormat.isStringEqualSensitive(userName, entry.getKey())){
			    	activeRate = entry.getValue().getActiveRate();
			    	activeAttempt = entry.getValue().getActiveAttempt();
			    	
			    	entry.getValue().setLastActiveTime(DateAdapter.getDateTime());
			    	
			    	if (isRate) {
			    		entry.getValue().setActiveRate(activeRate-1);
			    	}
			    	
			    	if (isAttempt) {
			    		entry.getValue().setActiveAttempt(activeAttempt-1);
			    	}
			    	
			    	userDetail = entry.getValue();
			    	break;
			    }
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return userDetail;
	}
}
