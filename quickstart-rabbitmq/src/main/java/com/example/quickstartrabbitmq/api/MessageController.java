package com.example.quickstartrabbitmq.api;

import com.example.quickstartrabbitmq.practices.DelayProducer;
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

	private final DelayProducer delayProducer;

	public MessageController(DelayProducer delayProducer) {
		this.delayProducer = delayProducer;
	}


	@PostMapping("/delay")
	public String sendMessage(@RequestBody String message) {
		delayProducer.send(message);
		return "成功";
	}
}
