package com.prav.util;

import java.util.List;
import java.util.Map;

import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Authenticator {
	@Value("${secure.user}")
	private String username;

	@Value("${secure.pass}")
	private String password;
	
	private Logger logger = Logger.getLogger(Authenticator.class);
	
	public void authenticate(MessageContext messageContext) {
		Map<String,List<String>> header = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
		List<String> userList = (List<String>) header.get("Username");
		List<String> passList = (List<String>) header.get("Password");

		String usernameReq = "";
		String passwd = "";

		if (userList != null) {
			usernameReq = userList.get(0).toString();
		}

		if (passList != null) {
			passwd = passList.get(0).toString();
		}
		BasicPasswordEncryptor passEncryptor = new BasicPasswordEncryptor();
		if (!(username.equals(usernameReq) && passEncryptor.checkPassword(passwd, password))) {
			throw new SecurityException("Invalid credentials");
		} else {
			logger.info("Successfully authenticated");
		}
	}
}
