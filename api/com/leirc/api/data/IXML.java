package com.leirc.api.data;

import java.io.File;

import com.leirc.api.exception.XMLWriteException;
import com.thoughtworks.xstream.XStream;

public interface IXML{
	public void writeData(XStream stream, File dir) throws XMLWriteException;
}