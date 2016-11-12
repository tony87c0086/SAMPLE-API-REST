package com.uknowho.sample.rest.constant;

/**
 * This ErrorMessageConstant class defines error message constants.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class ErrorMessageConstant {

	public ErrorMessageConstant() {
		
	}
	
	public static final String OBJECT_NOT_FOUND = 
			"Object not found.";

	// DB error message constants
	
	public static final String RECORD_NOT_FOUND = " Record(s) not found from database.";
	public static final String CONSTRAINT_FAILURE = "Record already exists in database."; 

	public static final String RECORD_SAVE_FAILURE = 
			"Error while processing saving record into database, "
			+ "id is not required for saving new record, "
			+ "while id is required for updating record.";
	public static final String RECORD_UPDATE_FAILURE = 
			"Error while processing updating record in database, "
			+ "id is required for updating record.";
	public static final String RECORD_MERGE_FAILURE = 
			"Error while processing merging record into database.";
	
	// Object mapping error message constants
	public static final String ENTITY_NOT_FOUND = "Entity is not found.";
	public static final String COPY_ENTITY_TO_MODEL_EXCEPTION = 
			"Error while copying entity to model.";
	
	public static final String MODEL_NOT_FOUND = "Model is not found.";
	public static final String COPY_ENTITY_FROM_MODEL_EXCEPTION = 
			"Error while copying entity from model.";
	
	public static final String CREATE_ENTITY_EXCEPTION = "Error while creating new entity.";
	
	public static final String MODEL_MAPPING_EXCEPTION = "Error while mapping object(s).";
	
	// Business logic error message constants
	public static final String CATALOGUE_NOT_FOUND = 
			"Catalogue is not found from database for further processing request.";
	public static final String CATALOGUE_MODEL_NOT_FOUND = 
			"Catalogue model is not found for further processing request.";

	// Service error message constants
	public static final String CATALOGUE_SERVICE_ID_NOT_FOUND = 
			"Sorry, Catalogue ID could not be empty for further processing request.";
	
	public static final String WEB_SERVICE_EXCEPTION = 
			"Error while processing web service request.";
	
	// API exception error message constants 
	public static final String BAD_REQUEST = "Request body or variable is missing...";
	public static final String SERVICE_UNAVAILABLE = "Service not available...";
	public static final String NOT_FOUND = "Resource not found...";
	public static final String METHOD_NOT_ALLOWED = "Request method not allowed...";
	public static final String NOT_ACCEPTABLE = "Media type not acceptable...";
	public static final String UNSUPPORTED_MEDIA_TYPE = 
			"Media type not supportable. Try JSON content-type instead.";
	
	// API exception error message constants 
	public static final String AUTHENTICATION_USERNAME_PATTERN = 
			"Sorry, username could not be null or empty "
			+ "or contain any special characters, "
			+ "please check before processing.";
	
	public static final String AUTHENTICATION_USER_NOT_FOUND = 
			"Sorry, user not found according to passing authentication username, "
			+ "please check before processing.";
	
	public static final String AUTHENTICATION_USER_PASSWORD_MISMATCH = 
			"Sorry, username and password are not match, "
			+ "please check before processing.";
	
	public static final String AUTHENTICATION_ACTIVE_RATE_NOT_FOUND = 
			"Active request rate running out for the user, "
        	 	+ "please wait for next server reset time.";
	
	public static final String AUTHENTICATION_ACTIVE_ATTEMP_NOT_FOUND = 
			"Too much invalid attemps, Please wait for next server reset time.";
	
	// Normal exception error message constants 
	public static final String UNKNOWN_EXCEPTION = "Unknown error while processing request.";
	
}
