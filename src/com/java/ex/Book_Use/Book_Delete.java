package com.java.ex.Book_Use;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.java.ex.DB.BookDao;
import com.java.ex.DB.BookDto;
import com.java.ex.DB.BookModel;
import com.java.ex.DB.MemberDao;
import com.java.ex.UI.Book_Update_Page;
import com.java.ex.UI.MainFrame_client_sucess;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Book_Delete extends JPanel implements MouseListener{
	JTable table;
	public Book_Delete() {
		table = new JTable(new BookModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		table.addMouseListener(this);
		setLayout(null);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(58, 5, 452, 428);
        add(jscp1);
        
        JButton btnNewButton = new JButton("새로고침");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//기능...추가하삼...힣...
        	}
        });
        btnNewButton.setBounds(541, 93, 97, 23);
        add(btnNewButton);
	}
	

	 @Override
	 public void mouseClicked(MouseEvent e) {
	  int row = table.getSelectedRow();
	  BookDao bDao = new BookDao();
	  int book_number = (Integer) table.getModel().getValueAt(row,1);
	  String book_name = (String) table.getModel().getValueAt(row,2);
	
	  
	  try {
		  
		  if(bDao.getBookRentYN(book_number) != 1) {
				Book_Update_Page update = new Book_Update_Page(book_number);
				  update.setVisible(true);
				  // 책 번호
				  
		  
		  
		  int result = bDao.getBookDelete(book_number);
		  bDao.getBookDelete(book_number);
		  if(result == 1) {	


			  JOptionPane.showInputDialog(book_name, book_name + "도서 삭제");
				
				
			}
			else {
				JOptionPane.showMessageDialog(null, "삭제된 도서입니다만...?");
			}
		  
		  }else {
			  JOptionPane.showMessageDialog(null, "해당 도서가 대여 중입니다.");
			  
		  }
		  
		  
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  
	}
	  @Override
	  public void mousePressed(MouseEvent e) {
		  
	  }

	  @Override
	  public void mouseReleased(MouseEvent e) {
	   // TODO Auto-generated method stub
	   
	  }

	  @Override
	  public void mouseEntered(MouseEvent e) {
	   // TODO Auto-generated method stub
	   
	  }

	  @Override
	  public void mouseExited(MouseEvent e) {
	   // TODO Auto-generated method stub
	   
	  }

}