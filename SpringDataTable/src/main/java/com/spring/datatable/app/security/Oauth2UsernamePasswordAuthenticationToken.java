package com.spring.datatable.app.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class Oauth2UsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -1111745326072337820L;
	
	private String jwtToken;

	public Oauth2UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String jwtToken) {
		super(principal, credentials, authorities);
		this.jwtToken  = jwtToken;
	}
	
	public String getJwtToken() {
		return this.jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
