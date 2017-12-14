package com.prav.soap.impl;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.prav.util.Authenticator;

import au.com.prav.gcd.GcdPort;
import au.com.prav.gcd.GcdRequest;
import au.com.prav.gcd.RetreiveFirstResponse;

@Service
public class GcdSoapImpl implements GcdPort {
	
	private Logger logger=Logger.getLogger(GcdSoapImpl.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	WebServiceContext webServiceContext;
	
	@Autowired
	Authenticator authenticator;	

	@Override
	public RetreiveFirstResponse retrieveFirst(GcdRequest parameters) {
		logger.debug("Retrieve first request");
		authenticator.authenticate(webServiceContext.getMessageContext());
		RetreiveFirstResponse response = new RetreiveFirstResponse();
		int gcd = jmsTemplate.browse("outbound.queue", new BrowserCallback<Integer>() {
			public Integer doInJms(final Session session, final QueueBrowser browser) throws JMSException {
				int headGcd = 0;
				Enumeration enumeration = browser.getEnumeration();
				Map<String, String> map = null;
				if (enumeration.hasMoreElements()) {
					logger.debug("Reading first element in queue");
					Message msg = (Message) enumeration.nextElement();
					if (msg instanceof MapMessage) {
						MapMessage mapMessage = (MapMessage) msg;
						MessageConsumer consumer = session.createConsumer(browser.getQueue(),
								" JMSMessageID='" + mapMessage.getJMSMessageID() + "'");
						BigInteger i1 = new BigInteger(mapMessage.getString("i1"));
						BigInteger i2 = new BigInteger(mapMessage.getString("i2"));
						headGcd = i1.gcd(i2).intValue();
						consumer.receive();
						logger.debug("Deleted first element in queue");
					}
				}
				return headGcd;
			}
		});
		response.setFirstGcd(gcd);
		return response;
	}

}
