package com.leirc.gui.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.leirc.api.gui.menubar.LeIRCMenu;

public class ServerMenu extends LeIRCMenu{
	private static final long serialVersionUID = -4197506327055162634L;
	private static final JMenu menu = new JMenu("Server");
	
	public ServerMenu(){
		super(menu);
		
		JMenuItem connectButton = new JMenuItem("Connect");
		
		JMenuItem disconnectButton = new JMenuItem("Disconnect");
		
		menu.add(connectButton);
		menu.add(disconnectButton);
	}
}