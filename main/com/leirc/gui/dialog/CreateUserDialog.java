package com.leirc.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.leirc.LeIRC;
import com.leirc.api.user.User;
import com.leirc.api.user.UserHelper;

public class CreateUserDialog extends JDialog{
	private static final long serialVersionUID = -2619733709559624903L;
	
	private final JTextField usernameField;
	private final JLabel usernameLabel;
	private final JButton createButton;
	private final JButton cancelButton;
	
	public CreateUserDialog(JFrame parent){
		super(parent, "Create User", true);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints consts = new GridBagConstraints();
		
		consts.fill = GridBagConstraints.HORIZONTAL;
		
		usernameLabel = new JLabel("Username: ");
		consts.gridx = 0;
		consts.gridy = 0;
		consts.gridwidth = 1;
		panel.add(usernameLabel, consts);
		
		usernameField = new JTextField(20);
		consts.gridx = 1;
		consts.gridy = 0;
		consts.gridwidth = 2;
		panel.add(usernameField, consts);
		
		ActionListener buttonListener = new ButtonListener(parent);
		
		createButton = new JButton("Create");
		createButton.setActionCommand("create");
		createButton.addActionListener(buttonListener);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(buttonListener);
		
		JPanel bp = new JPanel();
		bp.add(createButton);
		bp.add(cancelButton);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	private String getUsername(){
		return usernameField.getText();
	}
	
	private final class ButtonListener implements ActionListener{
		private final JFrame frame;
		
		private ButtonListener(JFrame frame){
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand().equals("create")){
				User user = UserHelper.getOrCreate(getUsername());
				UserHelper.updateCurrentUser(user);
				frame.setTitle(user.getName() + "|LeIRC");
				LeIRC.config.updateProperty("LastUser", user.getName());
				dispose();
			} else if(event.getActionCommand().equals("cancel")){
				dispose();
			}
		}
	}
}