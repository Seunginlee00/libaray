package com.java.ex.UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.java.ex.DB.MemberDao;
import com.java.ex.DB.MemberDto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField pwField;
	private JTextField nameField;
	private JTextField emailField;
	private JButton saveBtn;
	private JButton cancelBtn;
	private JRadioButton clientRdbtn;
	private JRadioButton adminRdbtn;
	private ButtonGroup  group2;
	int result_id;

	public JoinFrame() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(127, 37, 185, 29);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		idLabel.setBounds(35, 46, 57, 15);
		contentPane.add(idLabel);
		
		JButton idCheckBtn = new JButton("중복체크");
		idCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDao mDao = new MemberDao();
				try {
					result_id = mDao.getIdCheck(idField.getText());
				if(result_id != 1) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다." );
					}
				else {
					JOptionPane.showMessageDialog(null, "다른 아이디를 입력해주세요." );
				}
					
				}catch (Exception e3) {
					// TODO: handle exception
				e3.printStackTrace();
				}
			}
		});
		idCheckBtn.setFont(new Font("굴림", Font.PLAIN, 16));
		idCheckBtn.setBounds(351, 37, 107, 26);
		contentPane.add(idCheckBtn);
		
		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		pwLabel.setBounds(35, 102, 80, 18);
		contentPane.add(pwLabel);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		nameLabel.setBounds(35, 168, 57, 15);
		contentPane.add(nameLabel);
		
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		emailLabel.setBounds(35, 232, 57, 15);
		contentPane.add(emailLabel);
		
		
		saveBtn = new JButton("저장");
		saveBtn.addActionListener(new ActionListener() {
			MemberDao mDao = null;
			MemberDto mDto = null;
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(result_id != 1) {
					if (clientRdbtn.isSelected()) {
						mDto = new MemberDto(idField.getText(), pwField.getText(), nameField.getText(), emailField.getText(), clientRdbtn.getText(), 0,0);	
					}else if (adminRdbtn.isSelected()) {
						mDto = new MemberDto(idField.getText(), pwField.getText(), nameField.getText(), emailField.getText(), adminRdbtn.getText(), 0,0);
					}
					
					mDao = new MemberDao();
					mDao.getMemberJoin(mDto);
					}else {
						JOptionPane.showMessageDialog(null, "다른 아이디를 입력해주세요." );
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (mDao != null) {
					JOptionPane.showConfirmDialog(idField, idField.getText() + "님 추가!");
				}
			}
		});
		saveBtn.setFont(new Font("굴림", Font.PLAIN, 16));
		saveBtn.setBounds(101, 388, 97, 23);
		contentPane.add(saveBtn);
	

		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idField.setText("");
				pwField.setText("");
				nameField.setText("");
				emailField.setText("");
			}
		});
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 16));
		cancelBtn.setBounds(252, 388, 97, 23);
		contentPane.add(cancelBtn);
		
		JLabel checkLabel = new JLabel("구분");
		checkLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		checkLabel.setBounds(35, 297, 57, 18);
		contentPane.add(checkLabel);
		
		pwField = new JPasswordField();
		pwField.setColumns(10);
		pwField.setBounds(127, 102, 185, 29);
		contentPane.add(pwField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(127, 162, 185, 29);
		contentPane.add(nameField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(127, 226, 185, 29);
		contentPane.add(emailField);
		
		clientRdbtn = new JRadioButton("일반");
		clientRdbtn.setBounds(140, 296, 66, 23);
		contentPane.add(clientRdbtn);
		
		adminRdbtn = new JRadioButton("관리자");
		adminRdbtn.setBounds(235, 296, 66, 23);
		contentPane.add(adminRdbtn);
		
	
		group2 = new ButtonGroup();
		group2.add(adminRdbtn);
		group2.add(clientRdbtn);
	}
	
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						JoinFrame frame = new JoinFrame();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}


