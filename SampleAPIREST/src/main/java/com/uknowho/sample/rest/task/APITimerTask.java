package com.uknowho.sample.rest.task;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.rest.advice.AuthenticationAdvice;
import com.uknowho.sample.rest.config.Configuration;
import com.uknowho.sample.rest.security.CustomUserDetails;
import com.uknowho.sample.rest.utility.DateAdapter;


/**
 * This APITimerTask class is Default default API timer tasks. 
 * 
 * Created date <02-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class APITimerTask {

	private static final Logger logger = LoggerFactory.getLogger(APITimerTask.class);
	
	private Timer timer = new Timer ();
	
	public Timer getTimer() {
		return this.timer;
	}
	
	public TimerTask hourlyTask = new TimerTask () {
	    @Override
	    public void run () {
	    	
	    	Date resetTime = DateAdapter.addHourToDate(Configuration.DEFAULT_API_TIMEOUT_HOUR);
	    	
	    	CustomUserDetails value = null;
	    	Iterator<Map.Entry<String,CustomUserDetails>> iter = null;
	    	
	    	iter = AuthenticationAdvice.getCustomUserMap().entrySet().iterator();
			while (iter.hasNext()) {
			    Map.Entry<String,CustomUserDetails> entry = iter.next();
				value = entry.getValue(); 
				
				// Reset next refresh time
				try {
					value.setResetTime(resetTime);
					value.setActiveRate(value.getMaxHourRate());
					value.setActiveAttempt(value.getMaxHourAttempt());
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
	    }
	};
	
}
