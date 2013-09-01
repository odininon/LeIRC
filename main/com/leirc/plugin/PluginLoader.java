package com.leirc.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.leirc.api.LeIRCApi;
import com.leirc.api.plugin.IPlugin;
import com.leirc.api.plugin.PluginManifest;
import com.leirc.api.rsrc.Resources;
import com.leirc.utils.FileUtils;

public final class PluginLoader{
	private PluginLoader(){}
	
	public static synchronized boolean loadPlugins(){
		try{
			Future<Boolean> ret = LeIRCApi.executor.submit(new PluginLoaderThread());
			return ret.get();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return false;
		}
	}
	
	private static final class PluginLoaderThread implements Callable<Boolean>{
		@Override
		public Boolean call() throws Exception{
			try{
				Thread.currentThread().setName(getClass().getSimpleName());
				
				List<File> manifests = FileUtils.getAllFilesIn(Resources.PLUGINS, ".json");
				
				System.out.println(String.format("Loading %s plugins", manifests.size()));
				
				for(File file : manifests){
					PluginManifest manifest = PluginManifest.loadData(LeIRCApi.gson, file);
					loadPlugin(manifest);
				}
				
				return true;
			} catch(Exception ex){
				ex.printStackTrace(System.err);
				return false;
			}
		}
		
		private void loadPlugin(PluginManifest manifest) throws Exception{
			if(manifest.isValid()){
				File jar = new File(Resources.PLUGINS, manifest.getJarName());
				URLClassLoader loader = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()});
				Class<?> clazz = loader.loadClass(manifest.getIPluginClasspath());
				IPlugin plugin = (IPlugin) clazz.newInstance();
				System.out.println(plugin);
			}
		}
	}
}