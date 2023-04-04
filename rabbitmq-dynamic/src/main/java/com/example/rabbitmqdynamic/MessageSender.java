package com.example.rabbitmqdynamic;

import org.springframework.web.bind.annotation.*;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
@RestController
@RequestMapping("/send")
public class MessageSender {

	private final DynamicProducer dynamicProducer;


	private final QueueManager queueManager;

	public MessageSender(DynamicProducer dynamicProducer, QueueManager queueManager) {
		this.dynamicProducer = dynamicProducer;
		this.queueManager = queueManager;
	}

	@PostMapping("/{source}")
	public String sendMessage(@PathVariable String source, @RequestBody String message) {
		dynamicProducer.send(source, message);
		return "成功";
	}

	@PostMapping("/{source}/stop")
	public String stopSend(@PathVariable String source) {
		queueManager.removeQueueIfExist(source);
		return "成功";
	}
}
