package com.leirc.cmd;

import javax.swing.JDialog;

import com.leirc.api.cmd.CommandBase;
import com.leirc.gui.GuiMainWindow;
import com.leirc.gui.dialog.CreateUserDialog;

public class CMDCreateUser extends CommandBase{
	private static final long serialVersionUID = -7106409139742390391L;

	public CMDCreateUser(){
		super("/user");
	}

	@Override
	public void run() {
		JDialog dialog = new CreateUserDialog(GuiMainWindow.getFrame());
		dialog.setVisible(true);
	}
}