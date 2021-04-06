package com.vsvdev.spring_rabbit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;


	/**
	 *
	 * Method to test direct, fanout, topic exchanges and query
	 * 	 !!!comment when use Header exchange
	 * @param name
	 * @return
	 */
	@GetMapping("/test/{name}")
	public String testAPI(@PathVariable("name") String name) {
		Person p = new Person(1L, name);
		rabbitTemplate.convertAndSend("Mob", p);
		rabbitTemplate.convertAndSend("DExchange", "mobile", p);
		rabbitTemplate.convertAndSend("FExchange", "", p);
		rabbitTemplate.convertAndSend("TExchange", "tv.mobile.ac", p);
		return "Success";
	}



	/**
	 *!!! Method to send Header exchange uncommit to test and commit first method
	 * @param name
	 * @return
	 * @throws IOException
	 */
	/*
	@GetMapping("/test/{name}")
	public String testAPI(@PathVariable("name") String name) throws IOException {
		Person p = new Person(1L, name);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(p);
		out.flush();
		out.close();
		
		byte[] byteMessage = bos.toByteArray();
		bos.close();
		
		Message message = MessageBuilder.withBody(byteMessage)
				.setHeader("item1", "mobile")
				.setHeader("item2", "television").build();
		
		rabbitTemplate.send("HExchange", "", message);
		
		return "Success";
	}

	 */




}
