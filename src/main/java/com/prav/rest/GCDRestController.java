package com.prav.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prav.dao.model.Numbers;
import com.prav.dao.service.GcdDaoService;

@RestController
@RequestMapping("/gcd")
public class GCDRestController {

	@Autowired
	private ConnectionFactory connectionFactory;
	private JmsTemplate jmsTemplate;
	
	private Logger logger = Logger.getLogger(GCDRestController.class);

	@Autowired
	GcdDaoService daoService;

	@PostConstruct
	public void init() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}

	@GetMapping("/pushdata")
	public String push(@RequestParam final int i1, @RequestParam final int i2) {
		logger.debug("Received push rest call");
		jmsTemplate.send("inbound.queue", new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setInt("i1", i1);
				message.setInt("i2", i2);
				return message;
			}
		});
		logger.debug("Received and processes push rest call ");
		return "OK";
	}

	@GetMapping("/getdata")
	public List<Numbers> list() {
		logger.debug("Received getdata rest request");
		List<Numbers> numberList = daoService.selectValues();
		return numberList;
	}

}