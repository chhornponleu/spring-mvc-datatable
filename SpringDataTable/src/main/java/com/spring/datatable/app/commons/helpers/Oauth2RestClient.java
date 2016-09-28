package com.spring.datatable.app.commons.helpers;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Oauth2RestClient extends RestTemplate {
	private String HOST_PROXY_ADDR;
	private String HOST_PROXY_PORT;
	
	@Autowired
	public void init(Environment env) {
//		this.HOST_PROXY_ADDR = env.getProperty("HOST.PROXY_ADDR");
//		this.HOST_PROXY_PORT = env.getProperty("HOST.PROXY_PORT");
//		this.setProxy();
	}
	
	public void setProxy() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.HOST_PROXY_ADDR, Integer.parseInt(this.HOST_PROXY_PORT)));
		clientHttpRequestFactory.setProxy(proxy);
		this.setRequestFactory(clientHttpRequestFactory); 
	}
	
}
