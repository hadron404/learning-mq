package com.example.delay.constants;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
public enum ExchangeName {
	DELAY_MESSAGE_DEFAULT("delay.message.default"),
	DLX("dlx.default");

	ExchangeName(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}
}
