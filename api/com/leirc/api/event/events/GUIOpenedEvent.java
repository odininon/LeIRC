package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;
import com.leirc.api.gui.IGUI;

public final class GUIOpenedEvent implements IEvent{
	private static final long serialVersionUID = -9016442145277808175L;
	private final IGUI gui;
	
	public GUIOpenedEvent(IGUI gui){
		this.gui = gui;
	}
	
	public IGUI getGuiOpened(){
		return this.gui;
	}

	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}