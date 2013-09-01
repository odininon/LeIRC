package com.leirc.api.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.leirc.api.data.IXML;
import com.leirc.api.exception.XMLReadException;
import com.leirc.api.exception.XMLWriteException;
import com.thoughtworks.xstream.XStream;

public final class PluginManifest implements IXML{
	private String pluginName;
	
	public PluginManifest(){}
	
	public String getName(){
		return this.pluginName;
	}
	
	public void setPluginName(String name){
		this.pluginName = name;
	}
	
	@Override
	public void writeData(XStream stream, File dir) throws XMLWriteException {
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
}
