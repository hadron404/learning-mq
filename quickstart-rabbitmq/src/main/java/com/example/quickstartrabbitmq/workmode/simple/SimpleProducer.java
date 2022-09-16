package com.example.quickstartrabbitmq.workmode.simple;

import com.example.quickstartrabbitmq.constants.QueueNames;
import com.example.quickstartrabbitmq.workmode.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
@Service
public class SimpleProducer implements Producer {

	private final RabbitTemplate rabbitTemplate;

	public SimpleProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send(String message) {
		String sendMsg = LocalDateTime.now() + " - 源于 Simple 模式生产：" + message;
		this.rabbitTemplate.convertAndSend(QueueNames.TEST, sendMsg);
	}

	@Override
	public void send(String message, long ttl) {

	}

	@Override
	public void send(String message, int sendTotal) {

	}
}
