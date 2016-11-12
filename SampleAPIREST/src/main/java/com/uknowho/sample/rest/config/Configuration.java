package com.uknowho.sample.rest.config;

import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * This Configuration class is Default each config constants configuration . 
 * It also contains methods to get string or long value from 
 * separate properties files based on the key. 
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class Configuration extends PropertyPlaceholderConfigurer {

	public static final int MIN_DEFAULT_RETURN_RECORD = 0;
	public static final int MAX_DEFAULT_RETURN_RECORD = 50;
	
	// Service code setting
	public static final int CATALOGUE_SERVICE_CODE = 10;
	
	// API security setting
	public static final String SESSION_TOKEN_KEY = "sessionkey";
	public static final String SECURITY_TOKEN_KEY = "securitykey";
	
	public static final int DEFAULT_API_TIMEOUT_HOUR = 1;

	// Utility setting
	public static final double FILE_SIZE_CURRENCY = 1024;
	public static final String MEGABYTE_UNIT = "MB";
	
	public static final String DATE_FORMAT_STRING = "yyyy-MM-dd";
	public static final String TIME_FORMAT_STRING = "HH:mm:ss";
	public static final String DATETIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	
	public static final String SORT_DESCEND_PREFIX = "-";
	public static final int SORT_ASC_FIELD_INDEX = 0;
	public static final int SORT_DESCEND_FIELD_INDEX = 1;
	
	// Pattern matching
	public static final String EMAIL_WITHOUT_NULL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)"
			+ "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";
	public static final String STRING_VALID_PATTERN = "^[A-Za-z0-9 _-]*$";
	
	// XML model with header changes
	public static final String XML_MODEL_GENERIC_SCHEMA_PARSE = 
			"xjc -extension "
			+ "src/main/resources/schema/*.xsd "
			+ "-b XMLBindings.xml "
			+ "-d src/main/java";
	
	// Generate model without header changes
	public static final String XML_MODEL_GENERIC_SCHEMA_PARSE_NO_HEADER = 
			"xjc -extension -no-header "
					+ "src/main/resources/schema/*.xsd "
					+ "-b XMLBindings.xml "
					+ "-d src/main/java";
	
	private static Properties properties;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory,
            Properties props) {
		properties = props;
	}
	
	public Configuration() {
		super();
	}
	
	public static String getConfigValue(String key) {
		return properties.getProperty(key);
	}
	
	public static Long getLongConfigValue(String key) {
		String configval = null;
		try {
			if (properties.getProperty(key) != null) {
				configval = properties.getProperty(key);
			} else {
				configval = "0";
			}
		} catch (Exception e) {
			configval = "0";
		}
		
		return Long.parseLong(configval);
	}
}
