package com.leirc.api.exception;

import java.io.File;

public final class JsonWriteException extends Exception{
	private static final long serialVersionUID = -7595373105131800136L;
	
	public JsonWriteException(File file){
		super(String.format("Error Writing JSON File: %s", file.getAbsolutePath()));
	}
}
