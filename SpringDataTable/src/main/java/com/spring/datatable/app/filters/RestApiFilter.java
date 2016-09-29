package com.spring.datatable.app.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerMapping;

import com.spring.datatable.app.events.CustomEventPublisher;

public class RestApiFilter extends GenericFilterBean {

	@Autowired
	CustomEventPublisher publisher;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			//publisher.publishEvent("Hello");
		} catch (Exception e) {

		} finally {
			chain.doFilter(request, response);
		}
	}

	protected String getUrl(ServletRequest request) {
		return (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	}
}