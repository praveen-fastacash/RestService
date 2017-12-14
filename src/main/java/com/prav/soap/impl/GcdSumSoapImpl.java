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

import au.com.prav.gcd.GcdRequest;
import au.com.prav.gcd.GcdSumPort;
import au.com.prav.gcd.GcdSumResponse;

@Service
public class GcdSumSoapImpl implements GcdSumPort {

	private Logger logger = Logger.getLogger(GcdSumSoapImpl.class);

	@Resource
	WebServiceContext webServiceContext;

	@Autowired
	GcdDaoService daoService;

	@Autowired
	Authenticator authenticator;

	@Override
	public GcdSumResponse gcdSum(GcdRequest request) {
		authenticator.authenticate(webServiceContext.getMessageContext());
		List<Numbers> numbers = daoService.selectValues();
		GcdSumResponse response = new GcdSumResponse();
		response.setGcdSum(GcdUtil.gcdSum(numbers));
		return response;
	}

}
