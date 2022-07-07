package com.example.quickstartrabbitmq.practices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DelayProducerTest {

	@Autowired
	private DelayProducer producer;

	@Test
	void send() {
		producer.send("123456789");
	}
}
