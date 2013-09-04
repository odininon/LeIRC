package com.leirc.api.cmd;

import java.io.Serializable;

public interface ICommand extends Serializable, Runnable{
	public String getUID();
	public void execute(String[] params);
}