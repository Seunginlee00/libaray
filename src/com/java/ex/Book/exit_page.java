package com.java.ex.Book;

import javax.swing.JPanel;

import com.java.ex.DB.MemberDao;
import com.java.ex.DB.MemberDto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class exit_page extends JPanel {
	public exit_page() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("종료하시겠습니까?");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(166, 128, 256, 35);
		add(lblNewLabel);
		
		JButton okBtn = new JButton("확인");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		okBtn.setFont(new Font("굴림", Font.PLAIN, 22));
		//okBtn.addActionListener(this);
		okBtn.setBounds(160, 225, 84, 58);
		add(okBtn);
		
		JButton cencalBtn = new JButton("취소");
		cencalBtn.setFont(new Font("굴림", Font.PLAIN, 22));
		cencalBtn.setBounds(332, 225, 90, 58);
		add(cencalBtn);
	}
	/*	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String  aeType = ae.getActionCommand();
		MemberDao mDao = null;
		MemberDto mDto = null;
		
		if(aeType.equals(okBtn.getText())) {
			try {
				mDto = new MemberDto(tf[0].getText(), tf[1].getText(), tf[2].getText(), tf[3].getText());
				mDao = new MemberDao();
				mDao.getMemberDtoRegiste(mDto);
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(mDao != null) {
				JOptionPane.showConfirmDialog(this, tf[1].getText() + "�� �߰�!");
			}
			// click �̺�Ʈ �߻��� null�� �ٽ� ����
			else if (aeType.equals(cencalBtn.getText())) {
				int size = caption.length;
				for(int i=0; i<size-1; i++) tf[i].setText("");
			}
		}

	}*/
}
