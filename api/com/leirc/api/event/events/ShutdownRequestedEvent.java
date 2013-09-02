package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;

public final class ShutdownRequestedEvent implements IEvent{
	private static final long serialVersionUID = -708492539918122702L;

	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}