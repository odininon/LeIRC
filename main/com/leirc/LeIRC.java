package com.leirc;

import java.util.Iterator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;
import com.leirc.api.LeIRCApi;
import com.leirc.api.cfg.Property;
import com.leirc.api.cmd.CommandHelper;
import com.leirc.api.event.EventHandler;
import com.leirc.api.event.events.ShutdownRequestedEvent;
import com.leirc.api.gui.GuiHelper;
import com.leirc.api.os.OS;
import com.leirc.api.rsrc.Resources;
import com.leirc.api.rsrc.SessionData;
import com.leirc.api.user.UserHelper;
import com.leirc.cfg.Configuration;
import com.leirc.cmd.CMDCheckProperty;
import com.leirc.cmd.CMDCreateUser;
import com.leirc.gui.GuiMainWindow;
import com.leirc.gui.menubar.FileMenu;
import com.leirc.gui.menubar.HelpMenu;
import com.leirc.gui.menubar.ServerMenu;
import com.leirc.plugin.PluginLoader;
import com.leirc.users.UserLoader;

public final class LeIRC{
	public static Configuration config = new Configuration();
	
	public static void main(String... args) throws Exception{
		loadResources();
		loadConfiguration();
		setLAF();
		setSessionData();
		registerMenubarItems();
		loadUsers();
		loadPlugins();
		addCommands();
		
		if(SessionData.DEBUG){
			debugSessionData();
			debugConfig();
		}
		
		start();
	}
	
	public static void registerMenubarItems(){
		GuiHelper.registerMenu(new FileMenu());
		GuiHelper.registerMenu(new ServerMenu());
		GuiHelper.registerMenu(new HelpMenu());
	}
	
	public static void addCommands() throws Exception{
		CommandHelper.registerCommand(new CMDCreateUser());
		CommandHelper.registerCommand(new CMDCheckProperty());
	}
	
	public static void setLAF() throws Exception{
		int laf = config.getProperty("LAF").asInteger();
		
		switch(laf)
		{
		case 0:
			WebLookAndFeel.install();
			break;
		case 1:
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			break;
		default:
			break;
		}
	}
	
	public static void setSessionData(){
		try{
			if(SessionData.CURRENT_OS.equals(OS.WINDOWS)){
				SessionData.WIN_COMPAT = true;
			} else{
				SessionData.WIN_COMPAT = false;
			}
			
			SessionData.FIRST_LAUNCH = config.getProperty("FirstTime").asBoolean();
			SessionData.DEBUG = config.getProperty("Debug").asBoolean();
			Resources.DEBUG = config.getProperty("Debug").asBoolean();
//			SessionData.CURRENT_VERSION = new LeVersion(VersionType.ALPHA, "1.4.0");
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void debugSessionData(){
		try{
			System.out.println("============ DEBUG ===========");
			System.out.print("Current OS: " + SessionData.CURRENT_OS + endCOS());
			System.out.println("Current User: " + SessionData.CURRENT_USER);
			System.out.println("Debug: " + SessionData.DEBUG);
			System.out.println("First Time: " + SessionData.FIRST_LAUNCH);
//			System.out.println("Current Version: " + SessionData.CURRENT_VERSION);
			System.out.println("==============================");
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	private static String endCOS(){
		if(SessionData.CURRENT_OS.equals(OS.WINDOWS)){
			return "(Windows Compat:" + SessionData.WIN_COMPAT + ")\n";
		} else{
			return "\n";
		}
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
			System.out.println("========== DEBUG ============");
			Iterator<Property> iterator = config.iterator();
			while(iterator.hasNext()){
				Property next = iterator.next();
				System.out.println(next);
			}
			System.out.println("=============================");
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
			SessionData.CURRENT_USER = UserHelper.getUser(config.getProperty("LastUser").asString());
			
			if(UserHelper.CURRENT == null || SessionData.CURRENT_USER == null){
				UserHelper.CURRENT = UserHelper.DEFAULT;
				SessionData.CURRENT_USER = UserHelper.DEFAULT;
				config.updateProperty("LastUser", "Default");
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void cleanup(int exit){
		try {
			EventHandler.postEvent(new ShutdownRequestedEvent(exit, null), true);
			if(SessionData.FIRST_LAUNCH){
				config.updateProperty("FirstTime", false);
			}
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
			config.addProperty("FirstTime", true);
			config.addProperty("LAF", 0);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void loadConfigurationData(){
		try{
			SessionData.DEBUG = config.getProperty("Debug").asBoolean();
			Resources.DEBUG = config.getProperty("Debug").asBoolean();
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