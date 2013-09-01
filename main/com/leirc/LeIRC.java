package com.leirc;

import com.leirc.cfg.Configuration;

public final class LeIRC{
	public static Configuration config = new Configuration();
	
	public static void main(String... args){
		loadConfiguration();
		cleanup(0);
	}
	
	public static void cleanup(int exit){
		try {
			config.write();
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
}