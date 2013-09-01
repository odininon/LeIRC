package com.leirc.api.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.leirc.api.exception.JsonWriteException;

public final class User implements IUser{
	private String name;
	
	public User(String name){
		this.name = name;
	}
	
	public String getJsonFileName(){
		return this.getName() + ".json";
	}
	
	@Override
	public void writeData(Gson gson, File dir) throws JsonWriteException {
		File output = new File(dir, this.getJsonFileName());
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
			gson.toJson(this, writer);
		} catch(Exception ex){
			throw new JsonWriteException(output);
		}
	}
	
	public static User loadData(Gson gson, File input){
		try(BufferedReader reader = new BufferedReader(new FileReader(input))){
			User user = null;
			
			user = gson.fromJson(reader, User.class);
			
			return user;
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return null;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj != null && obj instanceof User){
			if(this.getName().equals(((User) obj).getName())){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString(){
		return String.format("User|%s", this.getName());
	}
}