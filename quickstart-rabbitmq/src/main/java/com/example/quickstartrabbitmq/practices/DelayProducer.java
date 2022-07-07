package com.example.quickstartrabbitmq.practices;

import com.example.quickstartrabbitmq.constants.QueueNames;
import com.example.quickstartrabbitmq.workmode.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Service
public class DelayProducer implements Producer {
	private final RabbitTemplate rabbitTemplate;

	public DelayProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void send(String message) {
		int ttl = 20;
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setExpiration(String.valueOf(ttl * 1000));
		Message sendMessage = new Message(message.getBytes(), messageProperties);
		System.out.println("Message publish at :" + LocalDateTime.now());
		System.out.println("Expect receive at: " + LocalDateTime.now().plusSeconds(ttl));
		this.rabbitTemplate.convertAndSend(QueueNames.TEST_TTL, sendMessage);
	}

}
