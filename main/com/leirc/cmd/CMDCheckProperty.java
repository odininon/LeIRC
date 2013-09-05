package com.leirc.cmd;

import com.leirc.api.cmd.CommandBase;

public class CMDCheckProperty extends CommandBase{
	private static final long serialVersionUID = -9166100549419781636L;

	public CMDCheckProperty(){
		super("/ckp", "Opens dialog showing value of selected property in config");
	}

	@Override
	public void run() {
		
	}
}