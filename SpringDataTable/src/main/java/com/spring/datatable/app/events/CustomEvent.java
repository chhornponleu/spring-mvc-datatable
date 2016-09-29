package com.spring.datatable.app.events;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String message;

	public CustomEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	@Override
	public String toString() {
		return this.getTimestamp() + ": " + message;
	}
}
