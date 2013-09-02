package com.leirc.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.leirc.LeIRC;
import com.leirc.api.gui.GuiBase;
import com.leirc.api.user.UserHelper;

public final class GuiMainWindow extends GuiBase{
	private static final long serialVersionUID = 4225819715281091287L;
	private static final JFrame frame = new JFrame("Default|LeIRC");
	
	public GuiMainWindow(){
		super("main", frame);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent event){
				LeIRC.cleanup(0);
			}
		});
		frame.setTitle(UserHelper.CURRENT.getName() + "|LeIRC");
		frame.setSize(new Dimension(1000, 640));
	}
}