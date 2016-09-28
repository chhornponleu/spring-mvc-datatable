package com.spring.datatable.app.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

@Service
public class HttpUtils {

	/**
	 * Get Base URL Ex: http://10.120.65.80:8080
	 * 
	 * @param request
	 * @return String of base url
	 */
	public String getBaseURL(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

	/**
	 * Get Base URL Ex: http://10.120.65.80:8080/servletContext
	 * 
	 * @param request
	 * @return String of base context url
	 */
	public String getBaseContextURL(HttpServletRequest request) {
		return this.getBaseURL(request) + request.getContextPath();
	}

	/**
	 * Get Mapping Path with Context Path Ex: /servletContext/yourMappingHandler
	 * 
	 * @param request
	 * @return
	 */
	public String getContextMappingUrl(HttpServletRequest request) {
		return request.getContextPath() + this.getHandlerMappingUrl(request);
	}

	/**
	 * Get Mapping URL Without Context Path Ex: /yourMappingHandler
	 * 
	 * @param request
	 * @return
	 */
	public String getHandlerMappingUrl(HttpServletRequest request) {
		return (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	}
}
