package com.vsvdev.spring_rabbit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

	/**
	 * Method to test direct, fanout, topic exchanges and query
	 * !!!comment when use Header exchange
	 *
	 *
	 * @param p
	 */

	@RabbitListener(queues = "Mob")
	@RabbitListener(queues = "Mobb")
	@RabbitListener(queues = "Mobb1")
	@RabbitListener(queues = "Mobb2")
	public void getMessage(Person p) {
		System.out.println(p.getName());
	}




	/**
	 * Method to test Header Exchange
	 * !!!Uncoment this method to test HExchange and connent first method
	 * @param message
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	/*
	@RabbitListener(queues = "Mobb3")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput in = new ObjectInputStream(bis);
		Person p = (Person) in.readObject();
		in.close();
		bis.close();
		System.out.println(p.getName());
	}

	 */




}
