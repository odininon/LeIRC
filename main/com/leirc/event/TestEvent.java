package com.leirc.event;

import com.leirc.api.event.IEvent;

public class TestEvent implements IEvent{
	private static final long serialVersionUID = -5850143376607221392L;

	@Override
	public String getUID() {
		return "TestEvent";
	}
}