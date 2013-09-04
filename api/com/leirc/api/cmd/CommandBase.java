package com.leirc.api.cmd;

import com.leirc.api.LeIRCApi;

public abstract class CommandBase implements ICommand{
	private static final long serialVersionUID = -2737024987333493460L;
	private final String uid;
	private String[] params;
	
	protected CommandBase(String uid){
		this.uid = uid;
	}
	
	protected final String[] getParams(){
		return this.params;
	}
	
	@Override
	public final void execute(String[] params){
		this.params = params;
		LeIRCApi.addToThreadPool(this);
	}

	@Override
	public String getUID() {
		return this.uid;
	}
	
	@Override
	public String toString(){
		return String.format("CMD|%s", this.getUID());
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ICommand){
			return ((ICommand) obj).getUID().equals(this.getUID());
		} else{
			return false;
		}
	}
}