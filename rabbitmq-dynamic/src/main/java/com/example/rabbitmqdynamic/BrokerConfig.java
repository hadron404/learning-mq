package com.example.rabbitmqdynamic;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BrokerConfig {
	public final static String DYNAMIC_QUEUE = "queue.delay.dynamic";
	public final static String DELAY_EXCHANGE = "exchange.delay.dynamic";

	public BrokerConfig(DynamicConsumer dynamicConsumer) {
		this.dynamicConsumer = dynamicConsumer;
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory("localhost");
	}

	@Bean
	public RabbitAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}


	@Bean
	public CustomExchange dynamicDelayExchange() {
		Map<String, Object> args = new HashMap<>(1);
		// 这里使用直连方式的路由，如果想使用不同的路由行为，可以修改，如 topic
		args.put("x-delayed-type", "direct");
		return new CustomExchange(DELAY_EXCHANGE,
			"x-delayed-message", true, false, args);
	}
	@Bean
	public Queue dynamicQueue() {
		return new Queue(DYNAMIC_QUEUE, true);
	}


	@Bean
	public Binding binding(Queue dynamicQueue, CustomExchange dynamicDelayExchange) {
		return BindingBuilder.bind(dynamicQueue)
			.to(dynamicDelayExchange)
			.with(DYNAMIC_QUEUE)
			.noargs();
	}

	private final DynamicConsumer dynamicConsumer;

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(1);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		// 由于该方法限制队列名不能为空，设置一个初始队列
		container.setQueueNames(DYNAMIC_QUEUE);
		container.setMessageListener(dynamicConsumer);
		return container;
	}

}
