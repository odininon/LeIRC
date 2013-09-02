package com.leirc.api.prov;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.leirc.api.LeIRCApi;
import com.leirc.api.os.OS;

public final class LeIRCApiModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(OS.class).annotatedWith(Names.named("current")).toInstance(LeIRCApi.currentOS);
	}
}