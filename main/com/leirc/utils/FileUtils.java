package com.leirc.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public final class FileUtils{
	private FileUtils(){}
	
	public static final List<File> getAllFilesIn(File dir, final String type){
		return Arrays.asList(dir.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(type);
			}
		}));
	}
}