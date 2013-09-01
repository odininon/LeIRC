package com.leirc.api.exception;

import java.io.File;

public final class XMLWriteException extends Exception{
	private static final long serialVersionUID = -2194058365189201750L;
	
	public XMLWriteException(File file){
		super(String.format("Error Writing XML File: %s", file.getAbsolutePath()));
	}
}