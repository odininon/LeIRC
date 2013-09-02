package com.leirc.api.gui;

import java.io.Serializable;

public interface IGUI extends Serializable{
	public String getUID();
	public void openGui();
	public void closeGui();
}