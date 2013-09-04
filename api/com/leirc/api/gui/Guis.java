package com.leirc.api.gui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Guis{
	private static final List<IGUI> guis = new LinkedList<IGUI>();
	private static final List<IGUI> openedGuis = new LinkedList<>();
	
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
	
	public static void openGui(IGUI gui){
		if(!openedGuis.contains(gui)){
			openedGuis.add(gui);
		}
	}
	
	public static void closeGui(IGUI gui){
		if(isGuiOpened(gui.getUID())){
			openedGuis.remove(gui);
		}
	}
	
	public static IGUI getOpenedGui(String name){
		for(IGUI gui : openedGuis){
			if(gui.getUID().equals(name)){
				return gui;
			}
		}
		
		return null;
	}
	
	public static boolean isGuiOpened(String name){
		return getOpenedGui(name) != null;
	}
	
	public static List<IGUI> getRegisteredGuis(){
		return Collections.unmodifiableList(guis);
	}
	
	public static List<IGUI> getOpenedGuis(){
		return Collections.unmodifiableList(openedGuis);
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