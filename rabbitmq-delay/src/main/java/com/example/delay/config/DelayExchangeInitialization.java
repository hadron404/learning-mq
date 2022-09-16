package com.example.delay.config;

import com.example.delay.constants.ExchangeName;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/9
 */
@Configuration
public class DelayExchangeInitialization {
	public static CustomExchange getCustomExchange(ExchangeName exchangeName) {
		Map<String, Object> args = new HashMap<>(1);
		// 这里使用直连方式的路由，如果想使用不同的路由行为，可以修改，如 topic
		args.put("x-delayed-type", "direct");
		return new CustomExchange(exchangeName.getName(),
			"x-delayed-message", true, false, args);
	}
}
