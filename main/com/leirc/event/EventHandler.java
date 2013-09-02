package com.leirc.event;

import com.google.common.eventbus.Subscribe;
import com.leirc.api.event.events.PluginLoadedEvent;

public class EventHandler{
	@Subscribe
	public void handlePluginLoad(PluginLoadedEvent event){
		System.out.println(event.getUID() + ": " + event.getPluginLoaded());
	}
}