package com.leirc;

import com.leirc.api.LeIRCApi;
import com.leirc.api.rsrc.Resources;
import com.leirc.cfg.Configuration;
import com.leirc.plugin.PluginLoader;
import com.leirc.users.UserLoader;

public final class LeIRC{
	public static Configuration config = new Configuration();
	
	public static void main(String... args){
		loadResources();
		loadConfiguration();
		loadPlugins();
		loadUsers();
	}
	
	public static void loadPlugins(){
		try{
			PluginLoader.loadPlugins();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void loadUsers(){
		try{
			UserLoader.loadUsers();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void cleanup(int exit){
		try {
			config.write();
			LeIRCApi.executor.shutdown();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		System.out.println("Exit Code: " + exit);
		System.exit(exit);
	}
	
	public static void loadConfiguration(){
		try{
			if(config.fileExists()){
				config.read();
			} else{
				config.write();
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void loadResources(){
		try{
			Resources.checkDirs();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
}