package com.leirc.api;

import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.leirc.api.plugin.PluginManifest;
import com.leirc.api.prov.LeIRCApiModule;
import com.thoughtworks.xstream.XStream;

public final class LeIRCApi{
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final Injector injector = Guice.createInjector(new LeIRCApiModule());
	public static final EventBus bus = new EventBus(LeIRCApi.class.getSimpleName());
	public static final XStream stream = new XStream();
	
	static
	{
		stream.alias("manifest", PluginManifest.class);
	}
}