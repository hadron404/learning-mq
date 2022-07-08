package com.example.quickstartrabbitmq.utils;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/8
 */
public class AckUtil {
	public void ackOrNack(Message message, Channel channel, Runnable runnable) throws IOException {
		try {
			runnable.run();
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
	}
}
