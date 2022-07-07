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
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Configuration
public class QueueTTLInitialization {

	@Bean
	public Queue testTTL() {
		Map<String, Object> args = new HashMap<>(2);
		// x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
		args.put("x-dead-letter-exchange", ExchangeNames.DLX.getName());
		// x-dead-letter-routing-key  这里声明当前队列的死信路由key
		args.put("x-dead-letter-routing-key", QueueNames.TEST_TTL);
		// x-message-ttl  声明队列的TTL
		return QueueBuilder.durable(QueueNames.TEST_TTL)
			.withArguments(args)
			.build();
	//  new Queue(QueueNames.TEST_TTL, true, false, false, args);
	}
}
