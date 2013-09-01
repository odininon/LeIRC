package com.leirc.api.event.events;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.leirc.api.event.IEvent;
import com.leirc.api.os.OS;

public class OSDebugEvent implements IEvent{
	private static final long serialVersionUID = 4087422355644737530L;
	private OS currentOS;
	
	@Inject
	private OSDebugEvent(@Named("current") OS os){
		this.currentOS = os;
	}
	
	public void debug(){
		System.out.println(this.currentOS);
	}
	
	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}