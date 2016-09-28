package com.spring.datatable.app.commons;
//package com.wing.sdash.app.commons;
//
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import com.wing.sdash.app.commons.helpers.token.Token;
//import com.wing.sdash.app.commons.helpers.token.TokenResponse;
//import com.wing.sdash.app.security.Oauth2UsernamePasswordAuthenticationToken;
//
//@Service
//public class TokenServices {
//	
//	private String HOST_REST_API;
//	
//	@Autowired
//	public TokenServices(Environment env) {
//		this.HOST_REST_API = env.getProperty("HOST.RESTAPI_URL");
//		System.out.println("\n\n\n"+this.HOST_REST_API);
//	}
//	
//	@Autowired
//	Environment env;
//	
//	private static Log log = LogFactory.getLog(TokenServices.class);
//	private static RestTemplate restTemplate;
//
////	static {
////		disableSslVerification();
////	}
//
////	private void disableSslVerification() {
////		try {
////			// Create a trust manager that does not validate certificate chains
////			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
////				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
////					return null;
////				}
////
////				public void checkClientTrusted(X509Certificate[] certs, String authType) {
////				}
////
////				public void checkServerTrusted(X509Certificate[] certs, String authType) {
////				}
////			} };
////
////			// Install the all-trusting trust manager
////			SSLContext sc = SSLContext.getInstance("SSL");
////			sc.init(null, trustAllCerts, new java.security.SecureRandom());
////			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
////
////			// Create all-trusting host name verifier
////			HostnameVerifier allHostsValid = new HostnameVerifier() {
////				public boolean verify(String hostname, SSLSession session) {
////					return true;
////				}
////			};
////
////			// Install the all-trusting host verifier
////			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
////		} catch (NoSuchAlgorithmException e) {
////			e.printStackTrace();
////		} catch (KeyManagementException e) {
////			e.printStackTrace();
////		}
////	}
//
//	public String getTokenResponse(String username, String password) throws HttpClientErrorException {
//		log.info("Start Parsing getTokenResponse");
//
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//		map.add("password", password);
//		map.add("username", username);
//		map.add("grant_type", "password");
//		map.add("client_id", "mobileapps_ios");
//		map.add("client_secret", "16681c9ff419d8ecc7cfe479eb02a7a");
//		map.add("device_id", "testing");
//
//		log.info("Request Object : " + map);
//		String result = null;
//		result = initRestTemplate().postForObject(this.HOST_REST_API + "/oauth/token", map, String.class);
//
//		return result;
//	}
//
//	public TokenResponse getRefreshTokenResponse() {
//		log.info("Start Parsing RefreshToken");
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		Oauth2UsernamePasswordAuthenticationToken authentication = (Oauth2UsernamePasswordAuthenticationToken) securityContext .getAuthentication();
//
//		map.add("refresh_token", authentication.getJwtToken());
//		map.add("grant_type", "refresh_token");
//		map.add("client_id", "mobileapps_ios");
//		map.add("client_secret", "16681c9ff419d8ecc7cfe479eb02a7a");
//		map.add("device_id", "testing");
//		Token result = new Token();
//		try {
//			result = initRestTemplate().postForObject(this.HOST_REST_API + "/oauth/token", map, Token.class);
//		} catch (RestClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		TokenResponse tokenResponse = getTokenReponse(result.getToken());
//		return tokenResponse;
//	}
//
//	public String getRenewTokenResponse(String username, String password) throws HttpClientErrorException {
//		log.info("Start Parsing getTokenResponse");
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//		map.add("password", password);
//		map.add("username", username);
//		map.add("grant_type", "password");
//		map.add("client_id", "mobileapps_ios");
//		map.add("client_secret", "16681c9ff419d8ecc7cfe479eb02a7a");
//		map.add("device_id", "testing");
//
//		String result = null;
//		result = initRestTemplate().postForObject(this.HOST_REST_API + "/oauth/token", map, String.class);
//		
//		return result;
//	}
//
//	public TokenResponse getTokenReponse(String strTokenJson) {
//		TokenResponse token = new TokenResponse();
//		//token = gson.fromJson(TokenManager.parseJWT(strTokenJson), TokenResponse.class);
//		return token;
//	}
//
//	private static RestTemplate initRestTemplate() {
//		if (restTemplate == null) {
//			SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
//			Proxy proxy = null;
//			try {
//				proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.120.10.222", 8080));
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			clientHttpRequestFactory.setProxy(proxy);
//			restTemplate = new RestTemplate(clientHttpRequestFactory);
//			return restTemplate;
//		} else {
//			return restTemplate;
//		}
//
//	}
//
//	 String getLoginResponse(String username, String password)  {
//		log.info("Start Parsing getTokenResponse");
//		boolean result = false;
//		String url = null;
//		
//		url = this.HOST_REST_API + "/oauth/token";
//		
//		String json = "{\"username\":\"hakfong.lim@gmail.com\", \"password\":\"2Wa7NDvKmP5hOGMEo3SZKQ==\" , \"device_id\":\"123\" }";
//
//		RestTemplate rest = new RestTemplate();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", "application/json");
//		System.out.println("ACCESS_TOKEN :" + getRefreshTokenResponse().getAccess_token());
//		headers.add("Authorization", String.format("Bearer %s", getRefreshTokenResponse().getAccess_token()));
//		headers.add("Accept", "*/*");
//
//		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
//		ResponseEntity<String> responseEntity = initRestTemplate().exchange(url, HttpMethod.POST, requestEntity,
//				String.class);
//
//		HttpStatus status = responseEntity.getStatusCode();
//		return "test";
//	}
//
//	HttpHeaders createHeaders(String username, String password) {
//		return new HttpHeaders() {
//			{
//				String authHeader = "Bearer " + new String("e601240e-b18b-46a2-b8d1-95f87d73603c");
//				set("Authorization", authHeader);
//			}
//		};
//	}
//}
