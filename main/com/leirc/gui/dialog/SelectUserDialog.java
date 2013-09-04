package com.leirc.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.leirc.LeIRC;
import com.leirc.api.user.User;
import com.leirc.api.user.UserHelper;
import com.leirc.gui.GuiMainWindow;

public class SelectUserDialog extends JDialog{
	private static final long serialVersionUID = -8878222606531984391L;
	
	private final DefaultListModel<User> model = new DefaultListModel<>();
	private final JButton chooseButton = new JButton("Select");
	private final ActionListener buttonListener = new ButtonListener();
	private final JList<User> list = new JList<>(model);
	
	public SelectUserDialog(JFrame parent){
		super(parent, "Select User", true);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		for(User user : UserHelper.users){
			model.addElement(user);
		}
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(3);
		
		JScrollPane listScrollPane = new JScrollPane(list);
		
		panel.add(listScrollPane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(150, 85));
		
		chooseButton.setPreferredSize(new Dimension(125, 25));
		chooseButton.addActionListener(buttonListener);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(chooseButton, BorderLayout.WEST);
		buttonPanel.setPreferredSize(new Dimension(150, 35));
		
		this.add(panel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(GuiMainWindow.getFrame());
	}
	
	private final class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			User selected = list.getSelectedValue();
			
			if(selected != null && !selected.equals(UserHelper.CURRENT)){
				UserHelper.updateCurrentUser(selected);
				GuiMainWindow.getFrame().setTitle(selected.getName() + "|LeIRC");
				LeIRC.config.updateProperty("LastUser", selected.getName());
				dispose();
			}
		}
	}
}