package com.example.quickstartrabbitmq.constants;

/**
 * .
 *
 * @author zhouqiang
 * @since 2022/7/6
 */
public enum ExchangeName {
	DLX("dlx.default");

	ExchangeName(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}
}
