package com.example.quickstartrabbitmq.constants;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
public class QueueNames {
	public static final String TEST_DELAY = "queue.test.delay";

	public static final String TEST = "queue.test";

	/**
	 * 死信队列
	 * 被监听消费的队列
	 */
	public static final String TEST_DEAD = "queue.test.dead";
	/**
	 * 存放 TTL 消息的队列
	 * 不可监听
	 */
	public static final String TEST_TTL = "queue.test.ttl";

}
