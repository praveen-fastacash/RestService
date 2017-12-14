package com.prav.soap.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prav.dao.model.Numbers;
import com.prav.dao.service.GcdDaoService;
import com.prav.util.Authenticator;
import com.prav.util.GcdUtil;

import au.com.prav.gcd.GcdListPort;
import au.com.prav.gcd.GcdListResponse;
import au.com.prav.gcd.GcdRequest;

@Service
public class GcdListSoapImpl implements GcdListPort {
	
	private Logger logger=Logger.getLogger(GcdListSoapImpl.class);

	@Resource
	WebServiceContext webServiceContext;
	
	@Autowired
	GcdDaoService daoService;

	@Autowired
	Authenticator authenticator;
	
	@Override
	public GcdListResponse gcdList(GcdRequest request) {
		logger.debug("Gcd List request started");
		authenticator.authenticate(webServiceContext.getMessageContext());
		List<Numbers> numbers= daoService.selectValues();
		GcdListResponse response = new GcdListResponse();
		response.getGcdList().addAll(GcdUtil.calculateGcdList(numbers));
		logger.debug("Gcd List request completed");
		return response;
	}

}
