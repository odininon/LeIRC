package com.leirc.api.os;

public enum OS{
	WINDOWS,
	SOLARIS,
	LINUX,
	MAC;
	
	@Override
	public String toString(){
		switch(this)
		{
		case WINDOWS:
			return "Windows";
		case SOLARIS:
			return "Solaris";
		case LINUX:
			return "Linux";
		case MAC:
			return "Mac";
		default:
			return "Non-parsible";
		}
	}
}