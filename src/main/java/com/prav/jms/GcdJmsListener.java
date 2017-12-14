package com.prav.jms;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.prav.dao.service.GcdDaoService;

@Component
public class GcdJmsListener {

	@Autowired
	GcdDaoService daoService;

	private Logger logger = Logger.getLogger(GcdJmsListener.class);

	@JmsListener(destination = "inbound.queue")
	@SendTo("outbound.queue")
	public Map<String, Integer> receiveMessage(final Message jsonMessage) throws JMSException {
		logger.debug("Received message " + jsonMessage );
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (jsonMessage instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) jsonMessage;
			map.put("i1", mapMessage.getInt("i1"));
			map.put("i2", mapMessage.getInt("i2"));
			int i1 = mapMessage.getInt("i1");
			int i2 = mapMessage.getInt("i2");
			daoService.insertValues(i1, i2);
		}
		return map;
	}

}