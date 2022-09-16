package com.example.delay.practices;

import com.example.delay.constants.QueueNames;
import com.example.delay.utils.AckUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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
	public void receive(Message receive, Channel channel) {
		String messageBody = new String(receive.getBody());
		String msg = "Receive Message: " + messageBody + " Receive at: " + LocalDateTime.now();
		// 处理消息
		System.out.println(msg);
		// 消息确认
	}


	@RabbitListener(queues = QueueNames.TEST_DELAY)
	public void receive(Channel channel,
		@Payload String body,
		@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
		// 消息确认
		AckUtil.ackOrNack(deliveryTag, channel, () -> {
			String msg = "Receive Message: [ " + body + " ] success at: [ " + LocalDateTime.now() + " ]";
			// 处理消息
			System.out.println(msg);
		});
	}
}
