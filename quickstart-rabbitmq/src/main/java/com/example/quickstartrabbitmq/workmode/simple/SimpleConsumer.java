package com.example.quickstartrabbitmq.workmode.simple;

import com.example.quickstartrabbitmq.constants.QueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
@Component
public class SimpleConsumer {
	// 监听的队列名
	// @RabbitListener(queues = QueueNames.TEST)
	public void process(String name) {
		System.out.println("进行 Simple 模式消费：" + name);
	}
}
