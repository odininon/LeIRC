package com.leirc.api.event.events;

import com.leirc.api.event.IEvent;
import com.leirc.api.plugin.IPlugin;

public final class PluginLoadedEvent implements IEvent{
	private static final long serialVersionUID = -4862312021795783898L;
	private final IPlugin plugin;
	
	public PluginLoadedEvent(IPlugin plugin){
		this.plugin = plugin;
	}
	
	public IPlugin getPluginLoaded(){
		return this.plugin;
	}

	@Override
	public String getUID(){
		return getClass().getSimpleName();
	}
}