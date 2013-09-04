package com.leirc.api.plugin;

import java.io.Serializable;

public interface IPlugin extends Serializable{
	public void load() throws Exception;
}