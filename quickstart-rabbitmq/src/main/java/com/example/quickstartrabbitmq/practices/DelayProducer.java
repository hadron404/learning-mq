package com.example.quickstartrabbitmq.practices;

import com.example.quickstartrabbitmq.constants.DelayTaskConfig;
import com.example.quickstartrabbitmq.utils.SpecRabbitTemplate;
import com.example.quickstartrabbitmq.workmode.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@Service
public class DelayProducer implements Producer {
	@Autowired
	private SpecRabbitTemplate specRabbitTemplate;

	@Override
	public void send(String message) {
		this.specRabbitTemplate.publishMessageWithTTL
			(DelayTaskConfig.TEST.getTtlQueue(), message, DelayTaskConfig.TEST.getTtl());
	}

}
