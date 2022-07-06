package com.example.quickstartrabbitmq.workmode.simple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleProducerTest {

	@Autowired
	private SimpleProducer simpleProducer;

	@Test
	void send() {
		simpleProducer.send("字符串消息");
	}
}
