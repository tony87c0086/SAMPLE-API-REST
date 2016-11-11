package com.uknowho.sample.rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.uknowho.sample.rest.advice.APIPathAdvice;
import com.uknowho.sample.rest.advice.AuthenticationAdvice;
import com.uknowho.sample.rest.config.Configuration;
import com.uknowho.sample.rest.security.CustomUserDetails;

/**
 * This CustomBasicAuthenticationFilter class is define customized basic authentication. 
 * 
 * Created date <12-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(CustomBasicAuthenticationFilter.class);
	
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource; 
	
	private AuthenticationManager customAuthenticationManager;
	
	public CustomBasicAuthenticationFilter(AuthenticationManager customAuthenticationManager,
			AuthenticationEntryPoint customAuthenticationEntryPoint) {
		super(customAuthenticationManager, customAuthenticationEntryPoint);
		this.customAuthenticationManager = customAuthenticationManager;
		authenticationDetailsSource = new WebAuthenticationDetailsSource();
	}
	
	/**
	 * Make autowired dependency injection services available in the servlet container
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		ServletContext servletContext = getServletContext();
		
		WebApplicationContext webApplicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		AutowireCapableBeanFactory autowireCapableBeanFactory = 
				webApplicationContext.getAutowireCapableBeanFactory();
		
		autowireCapableBeanFactory.autowireBean(this);
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		logger.debug("CustomBasicAuthenticationFilter doFilterInternal called.");
		
		boolean validRequest = false;
		
		if (isMethodAuthorizationRequired(request.getMethod())) {
			if (isPathAuthenticationRequired(request.getServletPath())) {
				// Do WS Application Level Authentication 
				String userName = doAuthentication(request);
				
				if (userName != null) {
		        	
		        	// Update Client authentication information
					CustomUserDetails userDetail = 
							AuthenticationAdvice.updateAuthenticationRate(userName);
					
		        	// Do User Level Authorization
		        	validRequest = doAuthorization(request, userDetail.getAccountID());
		        	
		        	if (!validRequest) {
		        		// Update invalid request attempts
						AuthenticationAdvice.updateAuthenticationAttempt(userName);
		        	} 
		        	
		        	// Update Response Header
		        	updateResponseHeader(userDetail, request, response);
				} 
			} else {
				validRequest = true;
			}
		 } else if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
        	validRequest = true;
        	response.setStatus(HttpStatus.SC_OK);
        }
		
		if (validRequest) {
			filterChain.doFilter(request, response);
		} else {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		}
	}
	
	private String doAuthentication(final HttpServletRequest request) {
		boolean success = false;
		String username = null;
		try {
			
			logger.debug("Basic authentication called.");
			
			String header = request.getHeader("Authorization");

			if (header == null || !header.startsWith("Basic ")) {
				logger.debug("Basic authentication header doesnt exist..");
			} else {
				int TOKEN_LENGTH = 2;
				int USER_NAME_INDEX = 0;
				int PASSWORD_INDEX = 1;
				String[] tokens = extractAndDecodeHeader(header, request);
				
				if (tokens.length == TOKEN_LENGTH) {
					username = tokens[USER_NAME_INDEX];
					String password = tokens[PASSWORD_INDEX];
							
					logger.debug("Basic Authentication Authorization header found for user: "
							+ username);

					UsernamePasswordAuthenticationToken authToken = 
							new UsernamePasswordAuthenticationToken(username, password);
					
					authToken.setDetails(authenticationDetailsSource.buildDetails(request));
					
					Authentication authentication = customAuthenticationManager.
							authenticate(authToken);
					
					success = authentication.isAuthenticated();
					logger.debug("Authentication result: " + success);
				}
			}

		} catch (AuthenticationException e) {
			logger.error("AuthenticationException: " + e.toString());
			success = false;
		} catch (IOException e) {
			logger.error("IOException: " + e.toString());
			success = false;
		} catch (Exception e) {
			logger.error("AuthenticationException: " + e.toString());
			success = false;
		} finally {
			if (!success) {
				username = null;
			}
		}
		return username;
	}
	
	/**
	 * This method is used for determine the API call is public or private based on URL path
	 * @param path
	 * @return boolean
	 */
	private boolean isPathAuthenticationRequired(final String path) {
		logger.debug("RequestPath value: " + path);
		boolean authRequired = false;
		switch(path) {
			case 	APIPathAdvice.DEFAULT_API: authRequired = true;
					break;
			case 	APIPathAdvice.CATALOGUES: authRequired = true;
					break;
			case	APIPathAdvice.HOME_PAGE: authRequired = false;
					break;
			case	APIPathAdvice.INDEX_PAGE: authRequired = false;
					break;		
			default:
					authRequired = true;
					break;
		}
		return authRequired;
	}
	
	/**
	 * This method is used for determine the API call is public or private based on method
	 * @param method
	 * @return boolean
	 */
	private boolean isMethodAuthorizationRequired(final String method) {
		logger.debug("RequestMethod value : " + method);
		boolean authRequired = false;
		switch(method) {
			case 	"POST": authRequired = true;
					break;
			case 	"GET": authRequired = true;
					break;
			case 	"PUT": authRequired = true;
					break;
			case 	"PATCH": authRequired = true;
					break;
			case	"DELETE": authRequired = true;
					break;
			case	"OPTIONS": authRequired = false;
					break;
			default:
					authRequired = true;
					break;
		}
		return authRequired;
	}
	
	private boolean doAuthorization(
			final HttpServletRequest request, 
			final Integer accountID) {
		boolean validRequest = false;
		try {
			String token = request.getHeader(Configuration.SECURITY_TOKEN_KEY);
			
			logger.debug("Security Token : " + token);
			
			if (token != null) {

				// TODO Application level authorization implementation
				
			} else {
				logger.debug("Request Header does not exist.");
			}
		} catch (Exception e) {
			validRequest = false;
			logger.error(e.toString());
		} 
		validRequest = true; // TODO Change this after done application authorization 
		return validRequest;
	}
	
	private void updateResponseHeader(
			final CustomUserDetails userDetail, 
			final HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			String requestHeaders = request.getHeader("Access-Control-Request-Headers");
			String maxAge = "3600";
			
			response.setHeader(
					"Access-Control-Max-Age", 
					maxAge);
			response.setHeader(
					"Access-Control-Allow-Headers", 
					requestHeaders);
			response.setHeader(
					"Access-Control-Allow-Origin", 
					userDetail.getDomainRange());
	        response.setHeader(
	        		"Access-Control-Allow-Methods", 
	        		userDetail.getAllowMethod());
	        response.setHeader(
	        		"Access-Control-Max-Hour-Request-Rate", 
	        		userDetail.getMaxHourRate().toString());
	        response.setHeader(
	        		"Access-Control-Remaining-Request-Rate", 
	        		userDetail.getActiveRate().toString());
	        response.setHeader(
	        		"Access-Control-Max-Hour-Invalid-Attempt", 
	        		userDetail.getMaxHourAttempt().toString());
	        response.setHeader(
	        		"Access-Control-Remaining-Invalid-Attempt", 
	        		userDetail.getActiveAttempt().toString());
	        response.setHeader(
	        		"Access-Control-Last-Active-Time", 
	        		userDetail.getLastActiveTime().toString());
	        response.setHeader(
	        		"Access-Control-Next-Reset-Time", 
	        		userDetail.getResetTime().toString());
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	private String[] extractAndDecodeHeader(final String header, final HttpServletRequest request)
			throws IOException {
		String[] headerPair = null;
		try {
			byte[] base64Token = header.substring(6).getBytes("UTF-8");
			byte[] decoded = Base64.decode(base64Token);
			
			String token = new String(decoded, getCredentialsCharset(request));

			int delim = token.indexOf(":");

			if (delim == -1) {
				throw new BadCredentialsException("Invalid basic authentication token");
			}
			
			String userName = token.substring(0, delim);
			String password = token.substring(delim + 1);
			
			headerPair = new String[] {userName,password};
			
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		} 
		return headerPair;
	}
	
}
