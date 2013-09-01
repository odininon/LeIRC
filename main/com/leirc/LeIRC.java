package com.leirc;

import java.util.Iterator;

import com.leirc.api.LeIRCApi;
import com.leirc.api.cfg.Property;
import com.leirc.api.rsrc.Resources;
import com.leirc.cfg.Configuration;
import com.leirc.plugin.PluginLoader;
import com.leirc.users.UserLoader;

public final class LeIRC{
	public static Configuration config = new Configuration();
	
	public static void main(String... args) throws Exception{
		loadResources();
		loadConfiguration();
		loadPlugins();
		loadUsers();
		cleanup(0);
	}
	
	public static void debugConfig(){
		try{
			Iterator<Property> iterator = config.iterator();
			while(iterator.hasNext()){
				Property next = iterator.next();
				System.out.println(next);
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
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
			boolean done = UserLoader.loadUsers();
			do{}while(!done);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void cleanup(int exit){
		try {
			config.write();
			boolean done = UserLoader.offloadUserData();
			do{}while(!done);
			LeIRCApi.executor.shutdown();
			LeIRCApi.serializer.shutdown();
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
			
			config.addProperty("LastUser", "Default");
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