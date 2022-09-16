package com.example.quickstartrabbitmq.api;

import com.example.quickstartrabbitmq.workmode.simple.SimpleProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@RestController
@RequestMapping("/send")
public class MessageController {

	private final SimpleProducer simpleProducer;

	public MessageController(SimpleProducer simpleProducer) {
		this.simpleProducer = simpleProducer;
	}

	@PostMapping()
	public String sendMessage(@RequestBody String message) {
		simpleProducer.send(message);
		return "成功";
	}
}
