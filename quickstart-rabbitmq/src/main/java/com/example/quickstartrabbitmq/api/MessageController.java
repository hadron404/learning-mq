package com.example.quickstartrabbitmq.api;

import com.example.quickstartrabbitmq.practices.DelayProducer;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/delay/batch/{totalSendCount}")
	public String sendMessage(@PathVariable int totalSendCount, @RequestBody String message) {
		delayProducer.send(message, totalSendCount);
		return "成功";
	}


	@PostMapping("/delay")
	public String sendMessage(@RequestBody String message) {
		delayProducer.send(message);
		return "成功";
	}

	@PostMapping("/delay/{ttl}")
	public String sendMessage(@PathVariable long ttl, @RequestBody String message) {
		delayProducer.send(message, ttl);
		return "成功";
	}
}
