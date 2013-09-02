package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;
import com.leirc.api.gui.IGUI;

public final class GUIClosedEvent implements IEvent{
	private static final long serialVersionUID = -1312977758929984454L;
	private final IGUI gui;
	
	public GUIClosedEvent(IGUI gui){
		this.gui = gui;
	}
	
	public IGUI getGuiClosed(){
		return this.gui;
	}

	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}