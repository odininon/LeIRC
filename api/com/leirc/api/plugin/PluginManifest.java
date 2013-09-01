package com.leirc.api.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.leirc.api.data.IJSON;
import com.leirc.api.exception.JsonWriteException;
import com.leirc.api.exception.XMLReadException;
import com.leirc.api.exception.XMLWriteException;
import com.thoughtworks.xstream.XStream;

public final class PluginManifest implements IJSON{
	private String pluginName;
	
	public PluginManifest(){}
	
	public String getName(){
		return this.pluginName;
	}
	
	public void setPluginName(String name){
		this.pluginName = name;
	}
	
	@Override
	public void writeData(Gson gson, File dir) throws JsonWriteException {
		File output = new File(dir, this.getXMLFileName());
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
			stream.toXML(this);
		} catch(Exception ex){
			throw new XMLWriteException(output);
		}
	}
	
	public static PluginManifest loadData(XStream stream, File xml) throws XMLReadException{
		try{
			return (PluginManifest) stream.fromXML(xml);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			throw new XMLReadException(xml);
		}
	}
	
	public String getXMLFileName(){
		return this.pluginName + ".xml";
	}
	
	@Override
	public String toString(){
		return String.format("Plugin (%s)", this.getName());
	}
}
