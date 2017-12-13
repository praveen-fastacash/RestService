package com.prav;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import au.com.prav.gcd.GcdRequest;
import au.com.prav.gcd.GcdService;
import au.com.prav.gcd.GcdSumPort;
import au.com.prav.gcd.GcdSumResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GcdSumSOAPIT {
	
	@Value("${local.server.port}")
    private int localServerPort;
	
	@Value("${secure.user}")
    private String username;
	
	@Value("${secure.key}")
    private String key;
	
	@Test
	public void testService() throws MalformedURLException
	{
		String url = "http://localhost:" + localServerPort +"/soapservice/gcdsum?wsdl";
		
		GcdSumPort port = new GcdService(new URL(url)).getGcdSum();
		Map<String, Object> req_ctx = ((BindingProvider)port).getRequestContext();
		req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Username", Collections.singletonList(username));
		headers.put("Password", Collections.singletonList("secret1"));
		req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		
		GcdSumResponse response = new GcdSumResponse();
		GcdRequest req = new GcdRequest();
		response = port.gcdSum(req);
		Assert.notNull(response.getGcdSum(), "Expected values not present");
	}

}
