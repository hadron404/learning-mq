package com.example.quickstartrabbitmq.config;

import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Exchange 初始化 .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
@Configuration
public class ExchangeInitialization {
	/**
	 * 默认的死信交换机
	 * 配置消息交换机
	 * 针对消费者配置
	 * FanoutExchange： 将消息分发到所有的绑定队列，无routing-key的概念
	 * HeadersExchange：通过添加属性key-value匹配
	 * DirectExchange：按照routing-key分发到指定队列
	 * TopicExchange：多关键字匹配
	 */
	// @Bean
	// public DirectExchange defaultDLX() {
	// 	return new DirectExchange(ExchangeName.DLX.getName(), true, false);
	// }
}
