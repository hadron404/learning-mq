package com.example.rabbitmqdynamic;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class QueueManager {
	private final SimpleMessageListenerContainer listenerContainer;

	private final RabbitAdmin rabbitAdmin;

	private final CustomExchange dynamicDelayExchange;

	public QueueManager(SimpleMessageListenerContainer listenerContainer, RabbitAdmin rabbitAdmin, CustomExchange dynamicDelayExchange) {
		this.listenerContainer = listenerContainer;
		this.rabbitAdmin = rabbitAdmin;
		this.dynamicDelayExchange = dynamicDelayExchange;
	}

	public Queue createQueueIfAbsent(String source) {
		Assert.hasText(source, "source can not be null or empty");
		String queueName = String.format("%s.%s", BrokerConfig.DYNAMIC_QUEUE, source);
		QueueInformation queueInfo = rabbitAdmin.getQueueInfo(queueName);
		Queue queue = new Queue(queueName);
		if (queueInfo == null) {
			rabbitAdmin.declareQueue(queue);
			listenerContainer.addQueues(queue);
			rabbitAdmin.declareBinding(
				BindingBuilder
					.bind(queue)
					.to(dynamicDelayExchange)
					.with(queue.getName())
					.noargs()
			);
		}
		return queue;
	}

	public void removeQueueIfExist(String source) {
		Assert.hasText(source, "source");
		String queueName = String.format("%s.%s", BrokerConfig.DYNAMIC_QUEUE, source);
		Queue queue = new Queue(queueName);
		// 移除队列与交换机的绑定关系
		// rabbitAdmin.removeBinding(BindingBuilder
		// 	.bind(queue)
		// 	.to(dynamicDelayExchange)
		// 	.with(queue.getName())
		// 	.noargs());
		// 监听容器中的队列会被移除，但是 rabbitMQ 中的队列不会被删除，在下次同样的queue被监听时，之前未发送的消息会重新被投递
		listenerContainer.removeQueueNames(queue.getName());
		// rabbitMQ 中的队列会删除
		rabbitAdmin.deleteQueue(queue.getName());
	}

}
