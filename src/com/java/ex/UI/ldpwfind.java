package com.java.ex.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.java.ex.DB.MemberDao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ldpwfind extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField idField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ldpwfind frame = new ldpwfind();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ldpwfind() {
		setTitle("비번찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel id = new JLabel("이름");
		id.setBounds(27, 103, 57, 15);
		contentPane.add(id);
		
		JLabel passwd = new JLabel("id");
		passwd.setBounds(27, 146, 72, 15);
		contentPane.add(passwd);
		
		nameField = new JTextField();
		nameField.setBounds(111, 96, 217, 29);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(111, 139, 217, 29);
		contentPane.add(idField);
		
		ButtonGroup group = new ButtonGroup();
		

		
		JButton btnNewButton = new JButton("찾기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDao mDao = new MemberDao();
				String result;
				try {
					result = mDao.getMemberLoginFind(nameField.getText(), idField.getText());
					JOptionPane.showMessageDialog(null, nameField.getText()+ "님의 비밀 번호는" + result + "입니다.");
					
				}catch (Exception e3) {
					// TODO: handle exception
				e3.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(156, 217, 97, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
