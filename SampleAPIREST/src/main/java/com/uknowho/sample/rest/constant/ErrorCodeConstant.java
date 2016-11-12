package com.uknowho.sample.rest.constant;

/**
 * This ErrorCodeConstant class defines error code constants.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class ErrorCodeConstant {

	public ErrorCodeConstant() {

	}
	
	/**
	 * 
	 * Error code generate format example:
	 * 
	 * 30 (error type) 001 (error code)
	 * 
	 * service code: 10 contact, 11 communication, 12 location, 13 description, 14 media
	 * 
	 * error type: 10 General error, 20 DB error, 30 Object mapping error, 
	 * 40 Business logic error, 50 service error, 99 normal error
	 * 
	 */
	
	/**
	 * 
	 */
	// Common error code constants
	public static final int OBJECT_NOT_FOUND = 10001;
	
	// DB error code constants
	public static final int HIBERNATE_EXCEPTION = 20001;
	
	public static final int RECORD_NOT_FOUND = 20002;
	public static final int CONSTRAINT_FAILURE = 20003;
	
	public static final int RECORD_SAVE_FAILURE = 20005;
	public static final int RECORD_UPDATE_FAILURE = 20006;
	public static final int RECORD_MERGE_FAILURE = 20007;
	
	// Object mapping error code constants
	public static final int ENTITY_NOT_FOUND = 30001;
	public static final int COPY_ENTITY_TO_MODEL_EXCEPTION = 30002;
	
	public static final int MODEL_NOT_FOUND = 30003;
	public static final int COPY_ENTITY_FROM_MODEL_EXCEPTION = 30004;
	
	public static final int CREATE_ENTITY_EXCEPTION = 30005;
	
	public static final int MODEL_MAPPING_EXCEPTION = 30009;
	
	// Business logic error code constants
	public static final int CATALOGUE_NOT_FOUND = 40001;
	public static final int CATALOGUE_MODEL_NOT_FOUND = 40002;
	
	// Service error code constants
	public static final int CATALOGUE_SERVICE_ID_NOT_FOUND = 50001;
	
	public static final int WEB_SERVICE_EXCEPTION = 50999;
	
	// API exception error code constants 
	
	// Normal exception error code constants 
	public static final int UNKNOWN_EXCEPTION = 99999;
	
}
