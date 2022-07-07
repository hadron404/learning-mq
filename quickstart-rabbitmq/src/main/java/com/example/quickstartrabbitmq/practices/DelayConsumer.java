package com.example.quickstartrabbitmq.practices;

import com.example.quickstartrabbitmq.constants.QueueNames;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
@Component
public class DelayConsumer {
	// 监听的队列名
	@RabbitListener(queues = QueueNames.TEST_DEAD)
	public void receive(Message receive, Channel channel) throws IOException {
		String messageBody = new String(receive.getBody());
		String msg = "Receive Message: " + messageBody + " Receive at: " + LocalDateTime.now();
		// throw new RuntimeException("接收消息处理异常");
		// 处理消息
		System.out.println(msg);
		// 消息确认
		// channel.basicAck(receive.getMessageProperties().getDeliveryTag(), false);
	}

}
