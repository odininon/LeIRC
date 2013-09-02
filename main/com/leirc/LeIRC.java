package com.leirc;

import java.util.Iterator;

import javax.swing.SwingUtilities;

import com.leirc.api.LeIRCApi;
import com.leirc.api.cfg.Property;
import com.leirc.api.rsrc.Resources;
import com.leirc.api.user.UserHelper;
import com.leirc.cfg.Configuration;
import com.leirc.gui.GuiMainWindow;
import com.leirc.plugin.PluginLoader;
import com.leirc.users.UserLoader;

public final class LeIRC{
	public static Configuration config = new Configuration();
	
	public static void main(String... args) throws Exception{
		loadResources();
		loadConfiguration();
		loadPlugins();
		loadUsers();
		start();
	}
	
	public static void start(){
		try{
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run(){
					GuiMainWindow main = new GuiMainWindow();
					main.openGui();
				}
			});
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
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
			UserHelper.CURRENT = UserHelper.getUser(config.getProperty("LastUser").asString());
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
			config.addProperty("Debug", true);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void loadResources(){
		try{
			Resources.checkDirs();
			Resources.clearEventCache();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
}