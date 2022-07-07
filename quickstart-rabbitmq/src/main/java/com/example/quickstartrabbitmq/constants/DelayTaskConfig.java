package com.example.quickstartrabbitmq.constants;

/**
 * 用于实现特定延时任务的队列交换机配置项.
 *
 * @author zhouqiang
 * @since 2022/7/7
 */
public enum DelayTaskConfig {

	TEST(QueueNames.TEST_TTL, QueueNames.TEST_DEAD, 10L);
	private final String ttlQueue;
	private final String deadQueue;
	private final ExchangeName dlxName;
	private final long ttl;

	DelayTaskConfig(String ttlQueue, String deadQueue, long ttl) {
		this(ttlQueue, deadQueue, ExchangeName.DLX, ttl);
	}

	DelayTaskConfig(String ttlQueue, String deadQueue, ExchangeName dlxName, long ttl) {
		this.ttlQueue = ttlQueue;
		this.deadQueue = deadQueue;
		this.dlxName = dlxName;
		this.ttl = ttl;
	}

	public String getTtlQueue() {
		return ttlQueue;
	}

	public String getDeadQueue() {
		return deadQueue;
	}

	public String getDlxName() {
		return dlxName.getName();
	}

	public long getTtl() {
		return ttl;
	}
}
