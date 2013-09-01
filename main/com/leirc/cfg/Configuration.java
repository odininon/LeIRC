package com.leirc.cfg;

import com.leirc.api.cfg.ConfigurationBase;
import com.leirc.api.rsrc.Resources;

public final class Configuration extends ConfigurationBase{
	public Configuration(){
		super("LeIRC", Resources.CFG);
	}

	@Override
	public void write() throws Exception {
		if(Resources.DEBUG){
			System.out.println(String.format("Writing Configuration: %s", this.getFile().getAbsolutePath()));
		}
		
		super.write();
		
		if(Resources.DEBUG){
			System.out.println(String.format("Done Writing Configuration: %s", this.getFile().getAbsolutePath()));
		}
	}

	@Override
	public void read() throws Exception {
		if(Resources.DEBUG){
			System.out.println(String.format("Reading Configuration: %s", this.getFile().getAbsolutePath()));
		}
		
		super.read();
		
		if(Resources.DEBUG){
			System.out.println(String.format("Done Reading Configuration: %s", this.getFile().getAbsolutePath()));
		}
	}
}