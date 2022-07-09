package com.example.quickstartrabbitmq.practices;

import com.example.quickstartrabbitmq.constants.DelayTaskConfig;
import com.example.quickstartrabbitmq.constants.ExchangeName;
import com.example.quickstartrabbitmq.constants.QueueNames;
import com.example.quickstartrabbitmq.utils.SpecRabbitTemplate;
import com.example.quickstartrabbitmq.workmode.Producer;
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
		this.specRabbitTemplate.publish(ExchangeName.DELAY_EXCHANGE.getName(), QueueNames.TEST_DELAY,
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
