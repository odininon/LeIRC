package com.leirc.users;

import java.io.File;
import java.util.List;

import com.leirc.api.LeIRCApi;
import com.leirc.api.rsrc.Resources;
import com.leirc.api.user.User;
import com.leirc.utils.FileUtils;

public final class UserLoader{
	public static void loadUsers(){
		try{
			LeIRCApi.executor.submit(new UserLoaderThread());
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	private static final class UserLoaderThread implements Runnable{
		@Override
		public void run(){
			try{
				List<File> users = FileUtils.getAllFilesIn(Resources.USERS, ".json");
				
				System.out.println(String.format("Loading %s users", users.size()));
				
				for(File file : users){
					User user = User.loadData(LeIRCApi.gson, file);
					loadUser(user);
				}
			} catch(Exception ex){
				ex.printStackTrace(System.err);
			}
		}
		
		private void loadUser(User user){
			System.out.println(String.format("Loading User: %s", user.toString()));
		}
	}
}