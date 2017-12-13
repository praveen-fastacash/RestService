package com.prav.jms;

import com.google.gson.Gson;
import com.prav.dao.service.GcdDaoService;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.HashMap;
import java.util.Map;

@Component
public class GcdJmsListener {
	
	@Autowired
	GcdDaoService daoService;

	@JmsListener(destination = "inbound.queue")
	@SendTo("outbound.queue")
	public Map<String, Integer> receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage + " " + daoService);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(jsonMessage instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage)jsonMessage;
			map.put("i1", mapMessage.getInt("i1"));
			map.put("i2", mapMessage.getInt("i2"));
			int i1 =mapMessage.getInt("i1");
			int i2 =mapMessage.getInt("i2");
			daoService.insertValues(i1,i2);
		}
		return map;
	}

}