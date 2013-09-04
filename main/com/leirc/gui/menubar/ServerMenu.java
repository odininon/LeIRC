package com.leirc.gui.menubar;

import javax.swing.JMenu;

import com.leirc.api.gui.menubar.LeIRCMenu;

public class ServerMenu extends LeIRCMenu{
	private static final JMenu menu = new JMenu("Server");
	
	protected ServerMenu(){
		super(menu);
	}
}