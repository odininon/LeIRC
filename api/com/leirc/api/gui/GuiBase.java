package com.leirc.api.gui;

import javax.swing.JFrame;

import com.leirc.api.event.EventHandler;
import com.leirc.api.event.events.GUIClosedEvent;
import com.leirc.api.event.events.GUIOpenedEvent;

public class GuiBase implements IGUI{
	private static final long serialVersionUID = -7816209987553255803L;
	private final String uid;
	private final JFrame frame;
	
	public GuiBase(String uid, JFrame frame){
		this.uid = uid;
		this.frame = frame;
		Guis.registerGui(this);
	}
	
	@Override
	public final String getUID() {
		return this.uid;
	}

	@Override
	public final void openGui() {
		EventHandler.postEvent(new GUIOpenedEvent(this), true);
		this.frame.setVisible(true);
	}

	@Override
	public final void closeGui() {
		EventHandler.postEvent(new GUIClosedEvent(this), true);
		this.frame.setVisible(false);
	}
}