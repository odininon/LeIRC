package com.leirc.api.user;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.leirc.api.LeIRCApi;
import com.leirc.api.rsrc.Resources;
import com.leirc.api.rsrc.SessionData;

public final class UserHelper{
	private UserHelper(){}
	
	public static final User DEFAULT = new User("Default");
	public static User CURRENT = DEFAULT;

	public static final List<User> users = new LinkedList<User>();
	
	static
	{
		users.add(DEFAULT);
	}
	
	public static File getUserFile(String name, File dir){
		return new File(dir, name + ".json");
	}
	
	public static File getUserFile(String name){
		return getUserFile(name, Resources.USERS);
	}
	
	public static void updateCurrentUser(User user){
		if(!users.contains(user)){
			users.add(user);
		}
		
		CURRENT = user;
		SessionData.CURRENT_USER = user;
	}
	
	public static void writeUserData(User user){
		try{
			user.writeData(LeIRCApi.gson, Resources.USERS);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static User getUser(String name){
		for(User user : users){
			if(user.getName().equals(name)){
				return user;
			}
		}
		
		if(hasUserFile(name)){
			return loadUserData(name);
		} else{
			return null;
		}
	}
	
	public static boolean userLoaded(String name){
		for(User user : users){
			if(user.getName().equals(name)){
				return true;
			}
		}
		
		return false;
	}

	public static User getOrCreate(String name){
		if(userLoaded(name)){
			return getUser(name);
		} else{
			return new User(name);
		}
	}
	
	public static boolean hasUserFile(String name){
		return getUserFile(name).exists();
	}
	
	public static User loadUserData(String name){
		try{
			if(userLoaded(name)){
				return getUser(name);
			} else{
				if(hasUserFile(name)){
					return User.loadData(LeIRCApi.gson, getUserFile(name));
				} else{
					return null;
				}
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return null;
		}
	}
}