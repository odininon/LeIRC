package com.leirc.plugin;

import java.io.File;
import java.util.List;

import com.leirc.api.LeIRCApi;
import com.leirc.api.plugin.PluginManifest;
import com.leirc.api.rsrc.Resources;
import com.leirc.utils.FileUtils;

public final class PluginLoader{
	private PluginLoader(){}
	
	public static void loadPlugins(){
		try{
			LeIRCApi.executor.submit(new PluginLoaderThread());
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	private static final class PluginLoaderThread implements Runnable{
		@Override
		public void run(){
			try{
				Thread.currentThread().setName(getClass().getSimpleName());
				
				List<File> manifests = FileUtils.getAllFilesIn(Resources.PLUGINS, ".xml");
				
				System.out.println(String.format("Loading %s plugins", manifests.size()));
				
				for(File file : manifests){
					PluginManifest manifest = PluginManifest.loadData(LeIRCApi.stream, file);
					loadPlugin(manifest);
				}
			} catch(Exception ex){
				ex.printStackTrace(System.err);
			}
		}
		
		private void loadPlugin(PluginManifest manifest){
			System.out.println(String.format("Loading Plugin: %s", manifest.toString()));
		}
	}
}