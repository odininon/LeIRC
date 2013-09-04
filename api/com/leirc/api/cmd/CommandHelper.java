package com.leirc.api.cmd;

import java.util.LinkedList;
import java.util.List;

import com.leirc.api.LeIRCApi;
import com.leirc.api.event.EventHandler;
import com.leirc.api.event.events.CommandSentEvent;
import com.leirc.api.rsrc.SessionData;

public final class CommandHelper{
	private CommandHelper(){}
	private static final List<ICommand> commands = new LinkedList<>();
	
	public static void registerCommand(ICommand command){
		if(commands.contains(command)){
			return;
		}
		
		commands.add(command);
	}
	
	public static ICommand get(String name){
		for(ICommand command : commands){
			if(command.getUID().equals(name)){
				return command;
			}
		}
		
		return null;
	}
	
	public static void executeCommand(String name, String[] params){
		ICommand command = get(name);
		if(command != null){
			EventHandler.postEvent(new CommandSentEvent(command), true);
			LeIRCApi.cmdExecutor.submit(command);
		} else{
			if(SessionData.DEBUG){
				System.out.println(String.format("Command Null (%s): %s", name, (params != null)));
			}
		}
	}
	
	public static boolean isCommand(String name){
		return get(name) != null;
	}
}