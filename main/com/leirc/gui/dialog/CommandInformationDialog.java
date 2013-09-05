package com.leirc.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.leirc.api.cmd.CommandHelper;

public class CommandInformationDialog extends JDialog{
	private static final long serialVersionUID = -1290871773666291256L;
	private static final JTable table = new JTable(CommandHelper.generateJTableArray(), new String[]{"Command", "Information"}){
		private static final long serialVersionUID = -6621100223311832209L;

		@Override
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	private static final JScrollPane scrollPane = new JScrollPane(table);
	
	public CommandInformationDialog(JFrame parent){
		super(parent, "Command Information", true);
		
		scrollPane.setPreferredSize(new Dimension(1000, 640));
		
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(parent);
	}
}