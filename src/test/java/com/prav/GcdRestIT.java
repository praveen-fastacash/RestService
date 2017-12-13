package com.prav;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GcdRestIT {
	
	@Value("${local.server.port}")
    private int localServerPort;
	
	@Value("${secure.user}")
	private String username;

	@Value("${secure.pass}")
	private String password;
	
	@Test
	public void testPush()
	{
		String url = "http://localhost:" + "8080" +"/gcd/pushdata?i1=25&i2=2";
		
		TestRestTemplate restTemplate = new TestRestTemplate(username, password);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	
}
