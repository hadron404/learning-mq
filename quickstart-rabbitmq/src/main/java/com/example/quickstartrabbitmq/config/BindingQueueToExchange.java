package com.example.quickstartrabbitmq.config;

import com.example.quickstartrabbitmq.constants.QueueNames;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列交换绑定关系初始化.
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Configuration
public class BindingQueueToExchange {

	// @Bean
	// Binding bindingTestDead(Queue testDead, DirectExchange defaultDLX) {
	// 	return BindingBuilder.bind(testDead)
	// 		.to(defaultDLX)
	// 		.with(DelayTaskConfig.TEST.getDeadQueue());
	// }

	// @Bean
	// Binding bindingTestDead(Queue testDelay, CustomExchange delayExchange) {
	// 	return BindingBuilder.bind(testDelay)
	// 		.to(delayExchange)
	// 		.with(QueueNames.TEST_DELAY)
	// 		.noargs();
	// }
}
