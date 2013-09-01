package com.leirc.api.exception;

import java.io.File;

public final class XMLReadException extends Exception{
	private static final long serialVersionUID = -2844637336178597001L;

	public XMLReadException(File file){
		super(String.format("Error Writing XML File: %s", file.getAbsolutePath()));
	}
}
