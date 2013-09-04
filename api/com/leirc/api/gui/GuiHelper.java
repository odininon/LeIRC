package com.leirc.api.gui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.leirc.api.gui.menubar.LeIRCMenu;

public final class GuiHelper{
	private static final List<LeIRCMenu> menus = new LinkedList<LeIRCMenu>();
	public static int MENU_ORDER_COUNT = 0;
	
	public static void registerMenu(LeIRCMenu menu){
		menus.add(menu.getOrderNo(), menu);
	}
	
	public static void injectMenuBarIntoFrame(JFrame frame){
		JMenuBar bar = new JMenuBar();
		
		for(LeIRCMenu menu : menus){
			bar.add(menu.getMenu());
		}
		
		frame.setJMenuBar(bar);
	}
}