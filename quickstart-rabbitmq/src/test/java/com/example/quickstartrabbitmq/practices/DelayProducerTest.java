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
		producer.send("delay message 1", 10L);
		producer.send("delay message 2", 20L);
		producer.send("delay message 3", 30L);
		// producer.send("delay No.", 10);
	}
}
