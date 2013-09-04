package com.leirc.gui.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.leirc.LeIRC;
import com.leirc.api.gui.menubar.LeIRCMenu;

public class FileMenu extends LeIRCMenu{
	private static final long serialVersionUID = -1410116335053915936L;
	private static final JMenu menu = new JMenu("File");
	
	public FileMenu(){
		super(menu);
		
		JMenuItem exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				LeIRC.cleanup(0);
			}
		});
		
		JMenuItem selectUserButton = new JMenuItem("User");
		selectUserButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
			}
		});
		
		menu.add(selectUserButton);
		menu.addSeparator();
		menu.add(exitButton);
	}
}