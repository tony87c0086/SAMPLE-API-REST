package com.uknowho.sample.rest.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * This CustomRole class is define customized spring security roles. 
 * 
 * Created date <12-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class CustomRole implements GrantedAuthority {

	private static final long serialVersionUID = -6045945997700410452L;
	
	private String roleName;
	   
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAuthority() {
        return this.roleName;
    }
}
