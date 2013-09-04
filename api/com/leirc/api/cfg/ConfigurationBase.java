package com.leirc.api.cfg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ConfigurationBase{
	private boolean needsUpdate = false;
	private final String name;
	private final File dir;
	private final List<Property> props = new LinkedList<Property>();
	
	protected ConfigurationBase(String name, File dir){
		this.name = name;
		this.dir = dir;
	}
	
	protected final File getFile(){
		return new File(this.dir, this.name + ".cfg");
	}
	
	public final boolean fileExists(){
		return this.getFile().exists();
	}
	
	protected boolean needsUpdate(){
		return this.needsUpdate;
	}
	
	protected final void flagUpdate(){
		if(!this.needsUpdate()){
			this.needsUpdate = true;
		}
	}
	
	public final List<Property> getProperties(){
		return this.props;
	}
	
	public final Property getProperty(String name){
		for(Property prop : this.getProperties()){
			if(prop.equals(Property.asDummy(name))){
				return prop;
			}
		}
		
		return null;
	}
	
	public final int getPropertyAt(String name){
		return this.getProperties().indexOf(Property.asDummy(name));
	}
	
	public final void addProperty(String name, Object value){
		this.addProperty(new Property(name, value));
	}
	
	public final void updateProperty(String name, Object newValue){
		int index = getPropertyAt(name);
		this.getProperties().remove(index);
		this.getProperties().add(index, new Property(name, newValue));
	}
	
	public final void addProperty(Property prop){
		if(!this.props.contains(prop)){
			this.props.add(prop);
		}
	}
	
	public final Iterator<Property> iterator(){
		return this.props.iterator();
	}
	
	public void write() throws Exception{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFile()))){
			for(Property prop : this.getProperties()){
				writer.write(prop.toString());
				writer.newLine();
				writer.flush();
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public void read() throws Exception{
		try(BufferedReader reader = new BufferedReader(new FileReader(this.getFile()))){
			String line;
			while((line = reader.readLine()) != null){
				props.add(Property.toProperty(line));
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
}
