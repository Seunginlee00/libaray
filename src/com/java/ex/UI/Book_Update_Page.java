package com.java.ex.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.java.ex.DB.BookDao;
import com.java.ex.DB.BookDto;
import com.java.ex.DB.MemberDao;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Book_Update_Page extends JFrame{

	
	private JPanel contentPane;
	private JTextField BookNumberField;
	private JTextField BookNameField;
	private JTextField BookAuthourField;
	private JTextField publisherField;

	public Book_Update_Page(int book_number) {
		setTitle("도서 수정");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel generalLabel = new JLabel("총류");
		generalLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		generalLabel.setBounds(32, 38, 55, 25);
		contentPane.add(generalLabel);
		
		JComboBox<String> generalBox = new JComboBox<String>();
		generalBox.setModel(new DefaultComboBoxModel<String>(new String[] {"000_총류", "100_철학", "200 종교", "300_사회과학", "400_자연과학", "500_기술과학", "600_예술", "700_언어", "800_문학", "900_역사"}));
		generalBox.setFont(new Font("굴림", Font.PLAIN, 16));
		generalBox.setBounds(115, 35, 182, 30);
		contentPane.add(generalBox);
		
		JLabel BookNameLabel = new JLabel("도서명");
		BookNameLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		BookNameLabel.setBounds(32, 147, 55, 25);
		contentPane.add(BookNameLabel);
		
		JLabel BookAuthourLabel = new JLabel("저자명");
		BookAuthourLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		BookAuthourLabel.setBounds(32, 204, 55, 25);
		contentPane.add(BookAuthourLabel);
		
		JLabel publisherLabel = new JLabel("출판사");
		publisherLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		publisherLabel.setBounds(32, 259, 55, 25);
		contentPane.add(publisherLabel);
		
		BookNameField = new JTextField();
		BookNameField.setFont(new Font("굴림", Font.PLAIN, 16));
		BookNameField.setColumns(10);
		BookNameField.setBounds(115, 150, 182, 25);
		contentPane.add(BookNameField);
		
		BookAuthourField = new JTextField();
		BookAuthourField.setFont(new Font("굴림", Font.PLAIN, 16));
		BookAuthourField.setColumns(10);
		BookAuthourField.setBounds(115, 207, 182, 25);
		contentPane.add(BookAuthourField);
		
		publisherField = new JTextField();
		publisherField.setFont(new Font("굴림", Font.PLAIN, 16));
		publisherField.setColumns(10);
		publisherField.setBounds(115, 262, 182, 25);
		contentPane.add(publisherField);
		
		JButton saveBtn = new JButton("수정");
		saveBtn.addActionListener(new ActionListener() {
			BookDao bDao = null;
			BookDto bDto = null;
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					bDto = new BookDto(generalBox.getSelectedItem().toString(), book_number, BookNameField.getText(), BookAuthourField.getText(), publisherField.getText(), 0, "Y", "N");	
				
					bDao = new BookDao();
					bDao.getBookUpdate(bDto);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (bDao != null) {
					JOptionPane.showConfirmDialog(BookNameField, BookNameField.getText() + "도서 수정");
				}
			}
		});
		saveBtn.setFont(new Font("굴림", Font.PLAIN, 16));
		saveBtn.setBounds(66, 323, 97, 40);
		contentPane.add(saveBtn);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookNumberField.setText("");
				BookNameField.setText("");
				BookAuthourField.setText("");
				publisherField.setText("");
			}
		});
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 16));
		cancelBtn.setBounds(237, 323, 97, 40);
		contentPane.add(cancelBtn);
		
	}




public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Book_Update_Page window = new Book_Update_Page(0);
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
}
