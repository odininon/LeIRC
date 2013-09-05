package com.leirc.gui.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.leirc.api.gui.menubar.LeIRCMenu;
import com.leirc.gui.GuiMainWindow;
import com.leirc.gui.dialog.CommandInformationDialog;

public class HelpMenu extends LeIRCMenu{
	private static final long serialVersionUID = -9084261083579585375L;
	private static final JMenu menu = new JMenu("Help");
	
	public HelpMenu(){
		super(menu);
		
		JMenuItem cmdTable = new JMenuItem("Command Table");
		cmdTable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				JDialog dialog = new CommandInformationDialog(GuiMainWindow.getFrame());
				dialog.setVisible(true);
			}
		});
		
		menu.add(cmdTable);
	}
}