package com.example.rabbitmqdynamic;

import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;

import static com.example.rabbitmqdynamic.BrokerConfig.DELAY_EXCHANGE;

@Service
public class DynamicProducer {

	private final QueueManager queueManager;
	private final SpecRabbitTemplate rabbitTemplate;

	public DynamicProducer(SpecRabbitTemplate rabbitTemplate, QueueManager queueManager) {
		this.rabbitTemplate = rabbitTemplate;
		this.queueManager = queueManager;
	}

	public void send(String source, String message) {
		Queue queue = queueManager.createQueueIfAbsent(source);
		this.rabbitTemplate.publish(DELAY_EXCHANGE, queue.getName(), message, 30);
	}
}
