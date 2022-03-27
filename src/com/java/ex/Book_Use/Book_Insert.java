package com.java.ex.Book_Use;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import com.java.ex.DB.BookModel;
import com.java.ex.UI.Book_Insert_Page;
import com.java.ex.UI.JoinFrame;
import com.java.ex.UI.LoginFrame;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Book_Insert extends JPanel {
	private JPanel panel;
	public Book_Insert() {
		setLayout(null);
		
		JTable table = new JTable(new BookModel());
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(224, 63, 502, 256);
        add(jscp1);
		
		
		
		JButton BookAddBtn = new JButton("도서 추가");
		BookAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_Insert_Page frame = new Book_Insert_Page();
				frame.setVisible(true);
			}
		});
		BookAddBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		BookAddBtn.setBounds(29, 145, 154, 73);
		add(BookAddBtn);
		
		
		
		
	}
}