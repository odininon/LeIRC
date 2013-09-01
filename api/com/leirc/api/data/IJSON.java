package com.leirc.api.data;

import java.io.File;

import com.google.gson.Gson;
import com.leirc.api.exception.JsonWriteException;

public interface IJSON{
	public void writeData(Gson gson, File dir) throws JsonWriteException;
}