package com.example.delay.practices;

import com.example.delay.constants.DelayTaskConfig;
import com.example.delay.constants.ExchangeName;
import com.example.delay.constants.QueueNames;
import com.example.delay.utils.SpecRabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Service
public class DelayProducer implements Producer {
	private final SpecRabbitTemplate specRabbitTemplate;

	public DelayProducer(SpecRabbitTemplate specRabbitTemplate) {
		this.specRabbitTemplate = specRabbitTemplate;
	}

	@Override
	public void send(String message) {
		send(message, DelayTaskConfig.TEST.getTtl());
	}

	@Override
	public void send(String message, long ttl) {
		// this.specRabbitTemplate.publishMessageWithTTL
		// 	(DelayTaskConfig.TEST.getTtlQueue(), message, ttl);
		this.specRabbitTemplate.publish(ExchangeName.DELAY_MESSAGE_DEFAULT.getName(), QueueNames.TEST_DELAY,
			message, (int) ttl);
	}

	@Override
	public void send(String message, int sendTotal) {
		IntStream.range(0, sendTotal)
			.forEachOrdered(i -> {
				long ttl = sendTotal - i;
				this.send(String.format(" %s  - %s", message, i), ttl);
			});
	}

}
