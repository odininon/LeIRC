package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;

public final class GUIClosedEvent implements IEvent{
	private static final long serialVersionUID = -1312977758929984454L;

	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}