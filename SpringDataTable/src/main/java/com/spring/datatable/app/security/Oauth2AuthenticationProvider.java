package com.spring.datatable.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import com.spring.datatable.app.dao.UserDao;
import com.spring.datatable.app.models.User;

public class Oauth2AuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getPrincipal().toString();
		String password = auth.getCredentials().toString();
		
		User dbUser = userDao.getByUsername(username);
		if(dbUser != null && dbUser.getUsername().equals(username)) {
			if(password.equals(dbUser.getPassword())) {
				List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
				roleList.add(new SimpleGrantedAuthority("ROLE_USER"));
				Oauth2UsernamePasswordAuthenticationToken token  = new Oauth2UsernamePasswordAuthenticationToken(username, password, roleList , "");
				return token;
			}
			else {
				throw new BadCredentialsException("Invalid password" );
			}
		}
		else {
			throw new BadCredentialsException("Invalid username");
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(Oauth2UsernamePasswordAuthenticationToken.class);
	}

}
