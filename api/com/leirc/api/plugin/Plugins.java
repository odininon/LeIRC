package com.leirc.api.plugin;

import java.util.HashMap;
import java.util.Map.Entry;

public final class Plugins{
	private Plugins(){}
	
	private static final HashMap<PluginManifest, IPlugin> pluginsMap = new HashMap<>();
	
	public static boolean pluginLoaded(String name){
		return getManifest(name) != null;
	}
	
	public static PluginManifest getManifest(String name){
		Entry<PluginManifest, IPlugin> entry = getPkgPlugin(name);
		
		if(entry != null){
			return entry.getKey();
		} else{
			return null;
		}
	}
	
	public static Entry<PluginManifest, IPlugin> getPkgPlugin(String name){
		for(Entry<PluginManifest, IPlugin> entry : pluginsMap.entrySet()){
			if(entry.getKey().getName().equals(name)){
				return entry;
			}
		}
		
		return null;
	}
}
