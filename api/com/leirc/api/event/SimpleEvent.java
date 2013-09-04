package com.leirc.api.event;

public abstract class SimpleEvent implements IEvent{
	private static final long serialVersionUID = -8904224244524361591L;
	
	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}