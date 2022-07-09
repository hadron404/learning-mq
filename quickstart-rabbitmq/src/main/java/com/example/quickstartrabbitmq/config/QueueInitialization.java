package com.example.quickstartrabbitmq.config;

import com.example.quickstartrabbitmq.constants.QueueNames;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ queue 初始化.
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
@Configuration
public class QueueInitialization {

	@Bean
	public Queue test() {
		return new Queue(QueueNames.TEST, true);
	}

	@Bean
	public Queue testDelay() {
		return new Queue(QueueNames.TEST_DELAY, true);
	}
}
