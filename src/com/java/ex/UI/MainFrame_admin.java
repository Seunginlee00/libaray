package com.java.ex.UI;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.java.ex.Book.exit_page;
import com.java.ex.Book.rental_page;
import com.java.ex.Book.rental_status_page;
import com.java.ex.Book.return_page;
import com.java.ex.Book_Use.Book_Delete;
import com.java.ex.Book_Use.Book_Insert;
import com.java.ex.Book_Use.Book_Update;
import com.java.ex.Book_Use.Client_Status;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame_admin extends JFrame {

	private JTabbedPane tp;
	private Book_Insert insert;
	private Book_Update update;
	private Book_Delete delete;
	private Client_Status clStatus;
	private exit_page exit;
	private JPanel panel;
	private JButton loginBtn;
	
	public MainFrame_admin() {
		getContentPane().setLayout(null);
		tp = new JTabbedPane();
		tp.setBounds(0, 61, 879, 385); 
		insert = new Book_Insert();
		update = new Book_Update();
		delete = new Book_Delete();
		clStatus = new Client_Status();
		exit = new exit_page();
		
		
		tp.addTab("도서추가", insert);
		tp.addTab("도서수정", update);
		tp.addTab("도서삭제", delete);
		tp.addTab("사용자현황", clStatus);
		tp.addTab("종료", exit);
		
		getContentPane().add(tp);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 879, 58);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		loginBtn = new JButton("로그인");
		loginBtn.setBounds(714, 10, 124, 38);
		panel.add(loginBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			LoginFrame login = new LoginFrame();
			login.setVisible(true);
				
			}
		});

		setTitle("도서 프로그램");
		
		setSize(895, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

		public static void main(String[] args) {
			new MainFrame_admin(); 
	}
}