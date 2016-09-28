package com.spring.datatable.app.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spring.datatable.app.security.Oauth2UsernamePasswordAuthenticationToken;

@Component("securityUtils")
public class SecurityUtils {

	public boolean isAuthenticated() {
		return (SecurityContextHolder.getContext().getAuthentication() != null)
				&& (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
	}

	public boolean isAnonymousUser() {
		return (SecurityContextHolder.getContext().getAuthentication() != null)
				&& (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
	}
	
	/**
	 * Get Token of current session
	 * @return <code>Oauth2UsernamePasswordAuthenticationToken</code>
	 */
	public  Oauth2UsernamePasswordAuthenticationToken getCurrentAuthenticatedToken() {
		Oauth2UsernamePasswordAuthenticationToken token = null;
		if(isAuthenticated()) {
			token = (Oauth2UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		}
		return token;
	}
}
