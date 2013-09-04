package com.leirc.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.leirc.api.os.OS;
import com.leirc.api.prov.LeIRCApiModule;
import com.thoughtworks.xstream.XStream;

public final class LeIRCApi{
	static
	{
		currentOS = getCurrentOS();
	}
	
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final Injector injector = Guice.createInjector(new LeIRCApiModule());
	public static final EventBus bus = new EventBus(LeIRCApi.class.getSimpleName());
	public static final XStream stream = new XStream();
	public static final ExecutorService executor = Executors.newFixedThreadPool(5);
	public static final ExecutorService serializer = Executors.newCachedThreadPool();
	public static final ExecutorService cmdExecutor = Executors.newCachedThreadPool();
	
	public static OS currentOS;
	
	private static OS getCurrentOS(){
		String osName = System.getProperty("os.name").toLowerCase();
		
		return (osName.indexOf("win") >= 0) ? OS.WINDOWS :
			   (osName.indexOf("mac") >= 0) ? OS.MAC :
			   (osName.indexOf("solaris") >= 0) ?  OS.SOLARIS :
			   (osName.indexOf("linux") >= 0) ? OS.LINUX : null;
	}
	
	public static void executeDeamon(Runnable runnable, String name){
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.setName(name);
		thread.start();
	}
	
	public static void addToThreadPool(Runnable runnable){
		executor.submit(runnable);
	}
	
	public static synchronized <T> T doTask(Callable<T> callable, int type){
		try{
			switch(type)
			{
			case -1:
				Future<T> serialExecutorFuture = serializer.submit(callable);
				return serialExecutorFuture.get();
			case 0:
				Future<T> future = executor.submit(callable);
				return future.get();
			case 1:
				Future<T> cmdExecutorFuture = cmdExecutor.submit(callable);
				return cmdExecutorFuture.get();
			default:
				return doTask(callable, 0);
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return null;
		}
	}
}