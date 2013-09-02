package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;

public final class GUIOpenedEvent implements IEvent{
	private static final long serialVersionUID = -9016442145277808175L;

	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}