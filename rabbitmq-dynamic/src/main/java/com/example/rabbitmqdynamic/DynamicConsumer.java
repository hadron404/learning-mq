package com.example.rabbitmqdynamic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
@Component
public class DynamicConsumer implements ChannelAwareMessageListener {
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		try {
			String consumerQueue = message.getMessageProperties().getConsumerQueue();
			String msg = new String(message.getBody());
			System.out.println("DynamicConsumer 收到 "+consumerQueue+" 队列消息 "+msg);
			channel.basicAck(deliveryTag, true);
		} catch (Exception e) {
			channel.basicReject(deliveryTag, false);
			e.printStackTrace();
		}
	}
}
