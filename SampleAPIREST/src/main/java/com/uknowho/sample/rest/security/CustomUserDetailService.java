package com.uknowho.sample.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.uknowho.sample.rest.advice.APIControllerAdvice;
import com.uknowho.sample.rest.advice.AuthenticationAdvice;
import com.uknowho.sample.rest.constant.ErrorMessageConstant;
import com.uknowho.sample.rest.utility.DataFormat;

/**
 * This CustomUserDetailService class is define customized spring security user detail services. 
 * 
 * Created date <12-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class CustomUserDetailService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(APIControllerAdvice.class);
	
	public CustomUserDetailService() {
		super();
	}
	
	@Override
	public CustomUserDetails loadUserByUsername(final String userName)
			throws UsernameNotFoundException {
		CustomUserDetails customUserDetail = null;
		try{
			logger.info("Loading user information for authentication.");
			
			if(!isUserNameValid(userName)) {
				throw new UsernameNotFoundException(
						ErrorMessageConstant.AUTHENTICATION_USERNAME_PATTERN);
			}
			
			customUserDetail = AuthenticationAdvice.getCustomUserDetails(userName);
			
			if (customUserDetail == null) {
				throw new UsernameNotFoundException(
						ErrorMessageConstant.AUTHENTICATION_USER_NOT_FOUND);
			} 
			
		} catch(Exception e) {
			logger.error(e.toString());
			throw new UsernameNotFoundException(e.toString());
		} 
		return customUserDetail;
	}

	private boolean isUserNameValid(final String userName){
		boolean isValid = false;
		try{
			logger.debug("Validating the pattern of username.");
			
			if (DataFormat.isStringValid(userName)){
				isValid = true;
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
			isValid = false;
		}
		return isValid;
	}

}
