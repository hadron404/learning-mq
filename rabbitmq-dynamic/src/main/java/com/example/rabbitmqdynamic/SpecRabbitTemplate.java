package com.example.rabbitmqdynamic;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
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

	public void publish(String exchange, String queue, String message, int seconds) {
		System.out.println("Message published at :" + LocalDateTime.now());
		System.out.println("Expect receive at: " + LocalDateTime.now().plusSeconds(seconds));
		this.rabbitTemplate.convertAndSend(exchange, queue, message,
			new DelayMessagePostProcessor(seconds));
	}

	static class DelayMessagePostProcessor implements MessagePostProcessor {
		private final Integer seconds;

		DelayMessagePostProcessor(Integer seconds) {
			this.seconds = seconds;
		}

		@Override
		public Message postProcessMessage(Message message) throws AmqpException {
			//设置消息持久化
			message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
			// 设置延迟时间
			message.getMessageProperties().setDelay(seconds * 1000);
			// 两种方式设置延迟时间同样
			// message.getMessageProperties().getHeaders().put("x-delay", delayTime);
			return message;
		}
	}

}
