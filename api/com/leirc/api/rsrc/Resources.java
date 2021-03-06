package com.leirc.api.rsrc;

import java.io.File;

public final class Resources{
	private Resources(){}
	
	public static boolean DEBUG = SessionData.DEBUG;
	
	public static final File DESKTOP = new File(new File(System.getProperty("user.home")), "Desktop");
	public static final File HOME = new File(new File(System.getProperty("user.home")), ".leirc");
	public static final File PLUGINS = new File(HOME, "plugins");
	public static final File CFG = new File(HOME, "configuration");
	public static final File LANG = new File(HOME, ".lang");
	public static final File BIN = new File(HOME, ".bin");
	public static final File USERS = new File(HOME, "users");
	public static final File SKINS = new File(HOME, ".skins");
	public static final File EVENT_CACHE = new File(BIN, "events");
	public static final File LOG = new File(BIN, "logs");
	
	public static void checkDirs(){
		checkDir(HOME);
		checkDir(PLUGINS);
		checkDir(CFG);
		checkDir(LANG);
		checkDir(BIN);
		checkDir(USERS);
		checkDir(SKINS);
		checkDir(EVENT_CACHE);
		checkDir(LOG);
	}
	
	public static void checkDir(File dir){
		if(!dir.exists()){
			if(DEBUG){
				System.out.println(String.format("Dir Doesn't Exist: %s", dir.getAbsolutePath()));
			}
			
			dir.mkdir();
			
			if(DEBUG){
				System.out.println(String.format("Created Dir: %s", dir.getAbsolutePath()));
			}
		} else{
			if(DEBUG){
				System.out.println(String.format("Dir Exists: %s", dir.getAbsolutePath()));
			}
		}
	}
	
	public static void clearEventCache(){
		try{
			for(File file : EVENT_CACHE.listFiles()){
				file.delete();
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
}