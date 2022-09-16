package com.example.quickstartrabbitmq.config;

import com.example.quickstartrabbitmq.constants.QueueNames;
import org.springframework.amqp.core.*;
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

	@Bean
	Binding bindingTestDead(Queue test, DirectExchange testExchange) {
		return BindingBuilder.bind(test)
			.to(testExchange)
			.with(QueueNames.TEST);
	}
}
