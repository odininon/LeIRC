package com.leirc.api.exception;

import java.io.File;

public final class JsonReadException extends Exception{
	private static final long serialVersionUID = 6396028850693852561L;
	
	public JsonReadException(File file){
		super(String.format("Error Reading JSON File: %s", file.getAbsolutePath()));
	}
}