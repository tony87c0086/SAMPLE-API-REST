package com.uknowho.sample.rest.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.uknowho.sample.rest.constant.ErrorMessageConstant;
import com.uknowho.sample.rest.exception.APIException;
import com.uknowho.sample.rest.xmlmodel.MessageModel;
import com.uknowho.sample.rest.xmlmodel.ResponseModel;

/**
 * This APIControllerAdvice class is Default default controller behaviour. 
 * 
 * Created date <02-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@ControllerAdvice
public class APIControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(APIControllerAdvice.class);
	
	private final String MORE_INFO_URL = APIPathAdvice.NAME_SPACE + APIPathAdvice.API_MORE_INFO;
	
	// Customized Exception
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ResponseModel> handleCustomException(final APIException ex) {
		ResponseModel response = null;
		
		logger.error("handleCustomException called.");
		
		if (ex.getResponseModel() != null) {
			// Customized service response model 
			response = ex.getResponseModel();
			response.setMoreInfo(MORE_INFO_URL + ex.getAPIPath());
		} else {
			// API level exception response model
			response = new ResponseModel();
			MessageModel message = new MessageModel();
			
			message.setCode(ex.getErrorCode().toString());
			message.setMessage(ex.getErrorMessage());
			
			response.getMessage().add(message);
			response.setMoreInfo(MORE_INFO_URL + ex.getAPIPath());
			response.setSuccess(false);
		}
		return new ResponseEntity<ResponseModel>(response, ex.getHttpStatus());
	}

	// Normal Exception 
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseModel> handleMissingBodyException(
			final HttpMessageNotReadableException ex) {
		ResponseModel response = new ResponseModel();
		MessageModel message = new MessageModel();
		
		logger.error("handleMissingBodyException called.");
		
		message.setCode(HttpStatus.BAD_REQUEST.toString());
		message.setMessage(ErrorMessageConstant.BAD_REQUEST);
		
		response.getMessage().add(message);
		response.setMoreInfo(MORE_INFO_URL + "/400");
		response.setSuccess(false);
		
		return new ResponseEntity<ResponseModel>(response, HttpStatus.BAD_REQUEST);
	}
	
	// Normal Exception 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseModel> handleAllException(final Exception ex) {
		ResponseModel response = new ResponseModel();
		MessageModel message = new MessageModel();
		
		logger.error("handleAllException called.");
		
		message.setCode(HttpStatus.SERVICE_UNAVAILABLE.toString());
		message.setMessage(ErrorMessageConstant.SERVICE_UNAVAILABLE);
		
		response.getMessage().add(message);
		response.setMoreInfo(MORE_INFO_URL);
		response.setSuccess(false);
		
		return new ResponseEntity<ResponseModel>(response, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	// 404 Error 
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseModel> handleError404(
    		final HttpServletRequest request, 
    		final Exception e) {
		
		logger.error("handleError404 called.");
		
		ResponseModel response = new ResponseModel();
		MessageModel message = new MessageModel();
		
		message.setCode(HttpStatus.NOT_FOUND.toString());
		message.setMessage(ErrorMessageConstant.NOT_FOUND);
		
		response.getMessage().add(message);
		response.setMoreInfo(MORE_INFO_URL + "/404");
		response.setSuccess(false);
		
		return new ResponseEntity<ResponseModel>(response, HttpStatus.NOT_FOUND);
    }
	
	// 405 Error 
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ResponseModel> handleError405(
    		final HttpServletRequest request, 
    		final Exception e) {
		
		logger.error("handleError405 called.");
		
		ResponseModel response = new ResponseModel();
		MessageModel message = new MessageModel();
		
		message.setCode(HttpStatus.METHOD_NOT_ALLOWED.toString());
		message.setMessage(ErrorMessageConstant.METHOD_NOT_ALLOWED);
		
		response.getMessage().add(message);
		response.setMoreInfo(MORE_INFO_URL + "/405");
		response.setSuccess(false);
		
		return new ResponseEntity<ResponseModel>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
	
	// 406 Error 
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ResponseModel> handleError406(
    		final HttpServletRequest request, 
    		final Exception e) {
		
		logger.error("handleError406 called.");
		
		ResponseModel response = new ResponseModel();
		MessageModel message = new MessageModel();
		
		message.setCode(HttpStatus.NOT_ACCEPTABLE.toString());
		message.setMessage(ErrorMessageConstant.NOT_ACCEPTABLE);
		
		response.getMessage().add(message);
		response.setMoreInfo(MORE_INFO_URL + "/406");
		response.setSuccess(false);
		
		return new ResponseEntity<ResponseModel>(response, HttpStatus.NOT_ACCEPTABLE);
    }
	
	// 415 Error 
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(value=HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ResponseModel> handleError415(
    		final HttpServletRequest request, 
    		final Exception e) {
		
		logger.error("handleError415 called.");
		
		ResponseModel response = new ResponseModel();
		MessageModel message = new MessageModel();
		
		message.setCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString());
		message.setMessage(ErrorMessageConstant.UNSUPPORTED_MEDIA_TYPE);
		
		response.getMessage().add(message);
		response.setMoreInfo(MORE_INFO_URL + "/415");
		response.setSuccess(false);
		
		return new ResponseEntity<ResponseModel>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
	
}
