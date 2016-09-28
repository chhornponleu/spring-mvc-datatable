package com.spring.datatable.app.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerMapping;

public class RestApiFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			System.out.println("\n\n");
			System.out.println("Rest API Filter has been passed for url:" + this.getUrl(request));
			System.out.println("\n\n");
		} catch (Exception e) {

		} finally {
			chain.doFilter(request, response);
		}
	}

	protected String getUrl(ServletRequest request) {
		return (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	}
}
