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
	public static void ackOrNack(Message message, Channel channel, Runnable runnable) throws IOException {
		ackOrNack(message.getMessageProperties().getDeliveryTag(), channel, runnable);
	}

	public static void ackOrNack(long deliveryTag, Channel channel, Runnable runnable) throws IOException {
		try {
			runnable.run();
			channel.basicAck(deliveryTag, false);
		} catch (Exception e) {
			channel.basicNack(deliveryTag, false, true);
		}
	}
}
