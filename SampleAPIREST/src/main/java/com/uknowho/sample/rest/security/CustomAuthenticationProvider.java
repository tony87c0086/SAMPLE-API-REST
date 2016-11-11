package com.uknowho.sample.rest.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uknowho.sample.rest.constant.ErrorMessageConstant;
import com.uknowho.sample.rest.utility.DataFormat;

/**
 * This CustomAuthenticationProvider class is define customized authentication provider. 
 * 
 * Created date <12-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
    private CustomUserDetailService customUserDetailService;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptEncoder;
	
	public CustomAuthenticationProvider() {
		super();
	}
	
	public CustomAuthenticationProvider(CustomUserDetailService customUserDetailService,
			BCryptPasswordEncoder bCryptEncoder) {
		super();
		this.customUserDetailService = customUserDetailService;
//		this.bCryptEncoder = bCryptEncoder;
	}
	
	@Override
	public Authentication authenticate(final Authentication authentication) 
			throws AuthenticationException {
		logger.debug("Authenticate provicder called.");
		
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
 
		CustomUserDetails user = null;
		try {
	 
			user = customUserDetailService.loadUserByUsername(userName);
	 
			// Authentication rule begin
			if ((user == null) 
					|| !(DataFormat.isStringEqualSensitive(user.getUsername(), userName))) {
				throw new BadCredentialsException(
						ErrorMessageConstant.AUTHENTICATION_USER_NOT_FOUND);
			}

			if (!DataFormat.isStringEqualSensitive(user.getPassword(), password)) {
				throw new BadCredentialsException(
						ErrorMessageConstant.AUTHENTICATION_USER_PASSWORD_MISMATCH);
			}
	 
			if (user.getActiveRate() <= 0) {
				throw new BadCredentialsException(
						ErrorMessageConstant.AUTHENTICATION_ACTIVE_RATE_NOT_FOUND);
			}
	 
			if (user.getActiveAttempt() <= 0) {
				throw new BadCredentialsException(
						ErrorMessageConstant.AUTHENTICATION_ACTIVE_ATTEMP_NOT_FOUND);
			}
			//Authentication rule end
			
		} catch (UsernameNotFoundException e) {
			throw new BadCredentialsException(e.toString());
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.toString());
		} 
 
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
