package com.leirc.api.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.leirc.api.data.IJSON;
import com.leirc.api.exception.JsonReadException;
import com.leirc.api.exception.JsonWriteException;

public final class PluginManifest implements IJSON{
	private String pluginName;
	private String jar;
	private String classPath;
	private String[] conflictedPlugins;
	private String[] requiredPlugins;
	
	public PluginManifest(){}
	
	public String getName(){
		return this.pluginName;
	}
	
	public void setRequiredPlugins(String[] plugins){
		this.requiredPlugins = plugins;
	}
	
	public void setConflictedPlugins(String[] plugins){
		this.conflictedPlugins = plugins;
	}
	
	public void setPluginName(String name){
		this.pluginName = name;
	}
	
	public void setJar(String jar){
		this.jar = jar;
	}
	
	public void setClasspath(String classPath){
		this.classPath = classPath;
	}
	
	public String[] getConflictedPlugins(){
		return this.conflictedPlugins;
	}
	
	public String[] getRequiredPlugins(){
		return this.requiredPlugins;
	}
	
	public String getIPluginClasspath(){
		return this.classPath;
	}
	
	public String getJarName(){
		return this.jar;
	}
	
	public boolean isValid(){
		return (this.getJarName() != null) &&
			   (this.getIPluginClasspath() != null) &&
			   (this.getName() != null);
	}
	
	@Override
	public void writeData(Gson json, File dir) throws JsonWriteException {
		File output = new File(dir, this.getXMLFileName());
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
			json.toJson(this);
		} catch(Exception ex){
			throw new JsonWriteException(output);
		}
	}
	
	public static PluginManifest loadData(Gson gson, File input) throws JsonReadException{
		try(BufferedReader reader = new BufferedReader(new FileReader(input))){
			return (PluginManifest) gson.fromJson(reader, PluginManifest.class);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			throw new JsonReadException(input);
		}
	}
	
	public String getXMLFileName(){
		return this.pluginName + ".xml";
	}
	
	@Override
	public String toString(){
		return String.format("Plugin (%s) -D%s -%s", this.getName(), this.getIPluginClasspath(), this.getJarName());
	}
}
