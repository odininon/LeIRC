package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;

public final class ShutdownRequestedEvent implements IEvent{
	private static final long serialVersionUID = -708492539918122702L;
	private final int exitCode;
	private final String exitMessage;
	
	public ShutdownRequestedEvent(int exitCode, String exitMessage){
		this.exitCode = exitCode;
		this.exitMessage = exitMessage;
	}
	
	public int getExitCode(){
		return this.exitCode;
	}
	
	public String getExitMessage(){
		return this.exitMessage;
	}

	@Override
	public String getUID() {
		return getClass().getSimpleName();
	}
}