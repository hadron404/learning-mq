package com.example.quickstartrabbitmq.config;

import com.example.quickstartrabbitmq.constants.DelayMessageConfig;
import com.example.quickstartrabbitmq.constants.DelayTaskConfig;
import org.springframework.amqp.core.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Configuration
public class MQDynamicInitialization implements BeanFactoryPostProcessor {
	/**
	 * Spring应用上下文环境
	 */
	private static ConfigurableListableBeanFactory beanFactory;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		MQDynamicInitialization.beanFactory = beanFactory;
	}

	@Bean
	public Object init() {
		Arrays.stream(DelayTaskConfig.values())
			.forEach(i -> {
				// 声明 ttl queue
				Queue ttlQueue = QueueTTLInitialization.delayTaskQueueBuild(i);
				beanFactory.registerSingleton(ttlQueue.getName(), ttlQueue);
				// 声明 死信队列
				Queue deadLetterQueue = new Queue(i.getDeadQueue(), true);
				beanFactory.registerSingleton(deadLetterQueue.getName(), deadLetterQueue);
				// 声明交换机
				DirectExchange directExchange = new DirectExchange(i.getDlxName(), true, false);
				beanFactory.registerSingleton(directExchange.getName(), directExchange);
				// 声明队列和交换的绑定关系
				Binding binding = BindingBuilder.bind(deadLetterQueue).to(directExchange).with(i.getDeadQueue());
				beanFactory.registerSingleton(deadLetterQueue.getName() + directExchange.getName(), binding);
				// Map<String, Object> map = new HashMap<>();
			});
		return null;
	}

	@Bean
	public Object initDelayMessage() {
		Arrays.stream(DelayMessageConfig.values())
			.forEach(i -> {
				// 声明队列
				Queue queue = new Queue(i.getQueue(), true);
				beanFactory.registerSingleton(queue.getName(), queue);
				// 声明交换机
				CustomExchange exchange = DelayExchangeInitialization.getCustomExchange(i.getExchange());
				beanFactory.registerSingleton(exchange.getName(), exchange);
				// 声明队列和交换的绑定关系
				Binding binding = BindingBuilder.bind(queue)
					.to(exchange)
					.with(i.getRoutingKey())
					.noargs();
				beanFactory.registerSingleton(queue.getName() + exchange.getName(), binding);
			});
		return null;
	}
}
