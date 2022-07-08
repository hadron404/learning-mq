package com.example.quickstartrabbitmq.utils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/8
 */
@Component
public class SpecRabbitTemplate {
	private final RabbitTemplate rabbitTemplate;

	public SpecRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void publishMessageWithTTL(String queue, String message, long seconds) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setExpiration(String.valueOf(seconds * 1000));
		Message sendMessage = new Message(message.getBytes(), messageProperties);
		System.out.println("Message publish at :" + LocalDateTime.now());
		System.out.println("Expect receive at: " + LocalDateTime.now().plusSeconds(seconds));
		this.rabbitTemplate.convertAndSend(queue, sendMessage);
	}
}
