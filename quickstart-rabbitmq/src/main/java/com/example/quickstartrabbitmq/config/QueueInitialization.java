package com.example.quickstartrabbitmq.config;

import com.example.quickstartrabbitmq.constants.ExchangeNames;
import com.example.quickstartrabbitmq.constants.QueueNames;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
	public Queue testDead() {
		return new Queue(QueueNames.TEST_DEAD, true);
	}

	@Bean
	public Queue testTTL() {
		Map<String, Object> args = new HashMap<>(2);
		// x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
		args.put("x-dead-letter-exchange", ExchangeNames.DLX);
		// x-dead-letter-routing-key  这里声明当前队列的死信路由key
		args.put("x-dead-letter-routing-key", QueueNames.TEST_TTL);
		return QueueBuilder.durable(QueueNames.TEST_TTL)
			.withArguments(args)
			.build();
	}

}
