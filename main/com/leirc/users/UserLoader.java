package com.leirc.users;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.leirc.api.LeIRCApi;
import com.leirc.api.rsrc.Resources;
import com.leirc.api.rsrc.SessionData;
import com.leirc.api.user.User;
import com.leirc.api.user.UserHelper;
import com.leirc.utils.FileUtils;

public final class UserLoader{
	public static synchronized boolean loadUsers(){
		try{
			Future<Boolean> ret = LeIRCApi.executor.submit(new UserLoaderThread());
			return ret.get();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return false;
		}
	}
	
	public static synchronized boolean offloadUserData(){
		try{
			Future<Boolean> ret = LeIRCApi.executor.submit(new UserOffLoaderThread());
			return ret.get();
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return false;
		}
	}
	
	private static final class UserLoaderThread implements Callable<Boolean>{
		@Override
		public Boolean call() throws Exception{
			try{
				List<File> users = FileUtils.getAllFilesIn(Resources.USERS, ".json");
				
				if(SessionData.DEBUG){
					System.out.println(String.format("Loading %s users", users.size()));
				}
				
				for(File file : users){
					loadUser(file);
				}
				
				return true;
			} catch(Exception ex){
				ex.printStackTrace(System.err);
				return false;
			}
		}
		
		private void loadUser(File file){
			UserHelper.users.add(User.loadData(LeIRCApi.gson, file));
		}
	}
	
	private static final class UserOffLoaderThread implements Callable<Boolean>{
		@Override
		public Boolean call() throws Exception{
			try{
				List<User> users = UserHelper.users;
				
				for(User user : users){
					if(user != null){
						UserHelper.writeUserData(user);
					}
				}
				
				return true;
			} catch(Exception ex){
				ex.printStackTrace(System.err);
				return false;
			}
		}
	}
}