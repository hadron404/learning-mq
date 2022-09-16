package com.example.delay.config;

import com.example.delay.constants.DelayTaskConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Configuration
public class QueueTTLInitialization {
	public static Queue delayTaskQueueBuild(DelayTaskConfig delayTask) {
		return QueueBuilder.durable(delayTask.getTtlQueue())
			// x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
			.deadLetterExchange(delayTask.getDlxName())
			// x-dead-letter-routing-key  这里声明当前队列的死信路由key
			.deadLetterRoutingKey(delayTask.getDeadQueue())
			.build();
		// x-message-ttl  声明队列的TTL
	}
}
