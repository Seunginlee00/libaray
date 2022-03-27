package com.java.ex.UI;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.java.ex.Book.exit_page;
import com.java.ex.Book.rental_page;
import com.java.ex.Book.rental_status_page;
import com.java.ex.Book.return_page;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame_client extends JFrame {

	private JTabbedPane tp;
	private rental_page rental;
	private return_page returns;
	private rental_status_page reStatus;
	private exit_page exit;
	private JPanel panel;
	private JButton loginBtn;
	
	public MainFrame_client() {
		getContentPane().setLayout(null);
		tp = new JTabbedPane();
		tp.setBounds(0, 61, 784, 385); 
		returns = new return_page(null);
		reStatus = new rental_status_page(null);
		exit = new exit_page();
		rental = new rental_page(null);
		
		tp.addTab("대여", rental);
		tp.addTab("반납", returns);
		tp.addTab("대출 상태", reStatus);
		tp.addTab("종료", exit);
		
		getContentPane().add(tp);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 784, 58);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		loginBtn = new JButton("로그인");
		loginBtn.setBounds(632, 10, 124, 38);
		panel.add(loginBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			LoginFrame login = new LoginFrame();
			login.setVisible(true);
			dispose();	
			}
		});

		setTitle("도서 프로그램");
		
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

		public static void main(String[] args) {
			new MainFrame_client(); 
	}
}