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

import au.com.prav.gcd.GcdListPort;
import au.com.prav.gcd.GcdListResponse;
import au.com.prav.gcd.GcdRequest;
import au.com.prav.gcd.GcdService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GcdListSOAPIT {
	
	@Value("${local.server.port}")
    private int localServerPort;
	
	@Test
	public void testService() throws MalformedURLException
	{
		String url = "http://localhost:" + localServerPort +"/soapservice/gcdlist?wsdl";
		
		GcdListPort port = new GcdService(new URL(url)).getGcdList();
		Map<String, Object> req_ctx = ((BindingProvider)port).getRequestContext();
		req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Username", Collections.singletonList("user1"));
		headers.put("Password", Collections.singletonList("secret1"));
		req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		
		GcdListResponse response = new GcdListResponse();
		GcdRequest req = new GcdRequest();
		response = port.gcdList(req);
		Assert.notNull(response.getGcdList(), "Expected values not present");
	}

}
