package com.spring.datatable.app.commons.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.datatable.app.commons.helpers.token.RefreshToken;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil {
	
	private static final Logger logger = Logger.getLogger(JwtTokenUtil.class);

	public String parseToken(String jwtToken) {
		JwtParser parser = Jwts.parser().setSigningKey("");
		parser.parseClaimsJws(jwtToken).getBody();
		parser.parseClaimsJws(jwtToken).getBody();
		return jwtToken;
	}

	public RefreshToken getRefreshToken(String jwtToken) {
		RefreshToken token = new RefreshToken();
		String parsedToken = this.parseToken(jwtToken);
		if (parsedToken != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				token = objectMapper.readValue(parsedToken, RefreshToken.class);
			} catch (IOException e) {
				logger.error("Error parsing token to RefreshToken", e);
			}
		}
		return token;
	}
}
