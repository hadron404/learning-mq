package com.example.quickstartrabbitmq.constants;

/**
 * 基于插件的队列交换机配置.
 *
 * @author zhouqiang
 * @since 2022/7/9
 */
public enum DelayMessageConfig {


	TEST(QueueNames.TEST_DELAY);

	private final String queue;
	private final ExchangeName exchange;

	DelayMessageConfig(String queue) {
		this(queue, ExchangeName.DELAY_MESSAGE_DEFAULT);
	}

	DelayMessageConfig(String queue, ExchangeName exchange) {
		this.queue = queue;
		this.exchange = exchange;
	}

	public String getQueue() {
		return queue;
	}

	public String getRoutingKey() {
		return getQueue();
	}

	public ExchangeName getExchange() {
		return exchange;
	}
}
