package main.java.vsvdev_spring_mvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class RabbitMQConsumer {

	/*@RabbitListener(queues = "Mob")
	@RabbitListener(queues = "Mobb")
	@RabbitListener(queues = "Mobb1")
	@RabbitListener(queues = "Mobb2")
	public void getMessage(Person p){
		System.out.println(p.getName());
	}*/
	
	@RabbitListener(queues = "Mobb3")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput in = new ObjectInputStream(bis);
		Person p = (Person) in.readObject();
		in.close();
		bis.close();
		System.out.println(p.getName());
	}
}
