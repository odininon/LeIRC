package com.leirc.api.gui.menubar;

import java.io.Serializable;

import javax.swing.JMenu;

import com.leirc.api.gui.GuiHelper;

public class LeIRCMenu implements Serializable{
	private static final long serialVersionUID = -2855955838328497140L;
	private final JMenu menu;
	private final int orderNo;
	
	protected LeIRCMenu(JMenu menu){
		this.orderNo = GuiHelper.MENU_ORDER_COUNT++;
		this.menu = menu;
	}
	
	public int getOrderNo(){
		return this.orderNo;
	}
	
	public JMenu getMenu(){
		return this.menu;
	}
}