package com.leirc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.leirc.LeIRC;
import com.leirc.api.cmd.CommandHelper;
import com.leirc.api.gui.GuiBase;
import com.leirc.api.user.UserHelper;

public final class GuiMainWindow extends GuiBase{
	private static final long serialVersionUID = 4225819715281091287L;
	private static final JFrame frame = new JFrame("Default|LeIRC");
	
	public GuiMainWindow(){
		super("main", frame);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent event){
				LeIRC.cleanup(0);
			}
		});
		frame.setTitle(UserHelper.CURRENT.getName() + "|LeIRC");
		frame.setSize(new Dimension(1000, 640));
		frame.add(new BottomPanel(), BorderLayout.SOUTH);
	}
	
	public static final JFrame getFrame(){
		return frame;
	}
	
	private final class BottomPanel extends JPanel{
		private static final long serialVersionUID = 8793265270776859470L;
		private final JButton enterButton = new JButton("Enter");
		private final JTextField inputField = new JTextField();
		
		private BottomPanel(){
			enterButton.setPreferredSize(new Dimension(125, 25));
			
			inputField.setPreferredSize(new Dimension(850, 25));
			
			this.setBackground(Color.PINK);
			this.setLayout(new FlowLayout());
			this.setPreferredSize(new Dimension(1000, 35));
			
			enterButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					String[] params = inputField.getText().split(" ");
					CommandHelper.executeCommand(params[0], params);
					inputField.setText("");
					return;
				}
			});
			
			this.add(enterButton, BorderLayout.WEST);
			this.add(inputField, BorderLayout.EAST);
		}
	}
}