package com.leirc.api.prov;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public final class LeIRCApiModule extends AbstractModule{
	@Override
	protected void configure() {
		
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