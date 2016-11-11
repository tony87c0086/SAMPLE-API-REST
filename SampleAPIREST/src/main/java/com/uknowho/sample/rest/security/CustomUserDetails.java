package com.uknowho.sample.rest.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * This CustomUserDetails class is define customized spring security user details. 
 * 
 * Created date <12-Aug-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -6596666112384787208L;
	
	private Integer accountID;
	private String token;
    private Date lastActiveTime;
    private Date resetTime;
    private Integer maxHourRate;
    private Integer activeRate;
    private Integer maxHourAttempt;
    private Integer activeAttempt;
    private String allowMethod;
    private String domainRange;
 
    /* Spring Security related fields*/
	private String username;
    private String password;
    private List<CustomRole> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    
	public Integer getAccountID() {
		return accountID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLastActiveTime() {
		return lastActiveTime;
	}
	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	public Date getResetTime() {
		return resetTime;
	}
	public void setResetTime(Date resetTime) {
		this.resetTime = resetTime;
	}
	public Integer getMaxHourRate() {
		return maxHourRate;
	}
	public void setMaxHourRate(Integer maxHourRate) {
		this.maxHourRate = maxHourRate;
	}
	public Integer getActiveRate() {
		return activeRate;
	}
	public void setActiveRate(Integer activeRate) {
		this.activeRate = activeRate;
	}
	public Integer getMaxHourAttempt() {
		return maxHourAttempt;
	}
	public void setMaxHourAttempt(Integer maxHourAttempt) {
		this.maxHourAttempt = maxHourAttempt;
	}
	public Integer getActiveAttempt() {
		return activeAttempt;
	}
	public void setActiveAttempt(Integer activeAttempt) {
		this.activeAttempt = activeAttempt;
	}
	public String getAllowMethod() {
		return allowMethod;
	}
	public void setAllowMethod(String allowMethod) {
		this.allowMethod = allowMethod;
	}
	public String getDomainRange() {
		return domainRange;
	}
	public void setDomainRange(String domainRange) {
		this.domainRange = domainRange;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<CustomRole> getAuthorities() {
		if (authorities == null) {
			authorities = new ArrayList<CustomRole>();
		}
		return authorities;
	}
	public void setAuthorities(List<CustomRole> authorities) {
		this.authorities = authorities;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
