package com.leirc.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.leirc.api.os.OS;
import com.leirc.api.plugin.PluginManifest;
import com.leirc.api.prov.LeIRCApiModule;
import com.thoughtworks.xstream.XStream;

public final class LeIRCApi{
	static
	{
		currentOS = getCurrentOS();
	}
	
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final Injector injector = Guice.createInjector(new LeIRCApiModule());
	public static final EventBus bus = new EventBus(LeIRCApi.class.getSimpleName());
	public static final XStream stream = new XStream();
	public static final ExecutorService executor = Executors.newFixedThreadPool(10);
	public static final ExecutorService serializer = Executors.newCachedThreadPool();
	
	public static OS currentOS;
	
	static
	{
		stream.alias("manifest", PluginManifest.class);
	}
	
	private static OS getCurrentOS(){
		String osName = System.getProperty("os.name").toLowerCase();
		
		return (osName.indexOf("win") >= 0) ? OS.WINDOWS :
			   (osName.indexOf("mac") >= 0) ? OS.MAC :
			   (osName.indexOf("solaris") >= 0) ?  OS.SOLARIS :
			   (osName.indexOf("linux") >= 0) ? OS.LINUX : null;
	}
}