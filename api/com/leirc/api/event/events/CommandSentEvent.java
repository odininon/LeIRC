package com.leirc.api.event.events;

import com.leirc.api.cmd.ICommand;
import com.leirc.api.event.SimpleEvent;

public final class CommandSentEvent extends SimpleEvent{
	private static final long serialVersionUID = -3063535903372059948L;
	private final ICommand command;
	
	public CommandSentEvent(ICommand command){
		this.command = command;
	}
	
	public ICommand getCommandSent(){
		return this.command;
	}
}