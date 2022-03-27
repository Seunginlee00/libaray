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
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField pwField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel id = new JLabel("ID");
		id.setBounds(42, 57, 57, 15);
		contentPane.add(id);
		
		JLabel passwd = new JLabel("PW");
		passwd.setBounds(42, 110, 57, 15);
		contentPane.add(passwd);
		
		idField = new JTextField();
		idField.setBounds(111, 50, 217, 29);
		contentPane.add(idField);
		idField.setColumns(10);
		
		pwField = new JPasswordField();
		pwField.setColumns(10);
		pwField.setBounds(111, 96, 217, 29);
		contentPane.add(pwField);
		
		
		
		
		JButton LoginBtn = new JButton("로그인");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDao mDao = null;
				try {
						mDao = new MemberDao();
						int result = mDao.getMemberLoginCheck(idField.getText(), pwField.getText());
						if(result == 1) {

							MainFrame_client_sucess  mcs = new MainFrame_client_sucess(idField.getText());
							mcs.setVisible(true);
							dispose();
							
						}
						else if(result == 2) {

							MainFrame_admin_access  mds = new MainFrame_admin_access();
							mds.setVisible(true);
							dispose();
							
						}
						else {
							JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 확인해 주세요");
						}
					}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LoginBtn.setForeground(Color.BLACK);
		LoginBtn.setBackground(Color.WHITE);
		LoginBtn.setBounds(82, 173, 97, 23);
		contentPane.add(LoginBtn);
		
		JButton JoinBtn = new JButton("회원가입");
		JoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinFrame frame = new JoinFrame();
				frame.setVisible(true);
			}
		});
		JoinBtn.setForeground(Color.BLACK);
		JoinBtn.setBackground(Color.WHITE);
		JoinBtn.setBounds(231, 173, 97, 23);
		contentPane.add(JoinBtn);
		
		JButton idpwBtn = new JButton("ID/PW 찾기");
		idpwBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ldpwfind frame = new ldpwfind();
				frame.setVisible(true);
			}
		});
		idpwBtn.setForeground(Color.BLACK);
		idpwBtn.setBackground(Color.WHITE);
		idpwBtn.setBounds(157, 219, 97, 23);
		contentPane.add(idpwBtn);
	}
	

}
