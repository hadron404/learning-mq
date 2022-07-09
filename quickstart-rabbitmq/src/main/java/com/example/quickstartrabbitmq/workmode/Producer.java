package com.example.quickstartrabbitmq.workmode;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
public interface Producer {
	void send(String message);

	void send(String message, long ttl);

	void send(String message, int sendTotal);
}
