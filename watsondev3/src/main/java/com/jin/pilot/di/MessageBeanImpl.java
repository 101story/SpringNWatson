package com.jin.pilot.di;

import org.springframework.stereotype.Component;

@Component("mbean")
public class MessageBeanImpl implements MessageBean {

	@Override
	public String sayHello() {
		return "Hello";
	}

}
