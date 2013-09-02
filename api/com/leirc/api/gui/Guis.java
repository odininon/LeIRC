package com.leirc.api.gui;

import java.util.LinkedList;
import java.util.List;

public class Guis{
	private static final List<IGUI> guis = new LinkedList<IGUI>();
	
	public static void registerGui(IGUI gui){
		guis.add(gui);
	}
	
	public static IGUI getGui(String name){
		for(IGUI gui : guis){
			if(gui.getUID().equals(name)){
				return gui;
			}
		}
		
		return null;
	}
	
	public static int getGuiAt(String name){
		for(int i = 0; i < guis.size(); i++){
			if(guis.get(i).getUID().equals(name)){
				return i;
			}
		}
		
		return 0;
	}
	
	public static void swapGui(String name, IGUI gui){
		int index = getGuiAt(name);
		guis.add(index, gui);
	}
}