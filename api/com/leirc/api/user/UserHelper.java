package com.leirc.api.user;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.leirc.api.LeIRCApi;
import com.leirc.api.rsrc.Resources;

public final class UserHelper{
	private UserHelper(){}
	
	public static final User DEFAULT = new User("Default");

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
	
	public static void writeUserData(User user){
		try{
			user.writeData(LeIRCApi.gson, Resources.USERS);
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static User loadUserData(String name){
		try{
			return User.loadData(LeIRCApi.gson, getUserFile(name));
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return null;
		}
	}
}