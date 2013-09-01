package com.leirc.api.prov;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.leirc.api.LeIRCApi;
import com.leirc.api.event.events.OSDebugEvent;
import com.leirc.api.os.OS;

public final class LeIRCApiModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(OS.class).annotatedWith(Names.named("current")).toInstance(LeIRCApi.currentOS);
		bind(OSDebugEvent.class);
	}
	
	@Provides
	@Named("desktop")
	public File provideDesktop(){
		return new File(new File(System.getProperty("user.home")), "Desktop");
	}
	
	@Provides
	@Named("home")
	public File provideHome(){
		return new File(new File(System.getProperty("user.home")), ".leirc");
	}
}