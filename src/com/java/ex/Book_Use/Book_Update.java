package com.java.ex.Book_Use;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.java.ex.DB.BookDao;
import com.java.ex.DB.BookModel;
import com.java.ex.UI.Book_Update_Page;

public class Book_Update extends JPanel implements MouseListener {
	JTable table;
	public Book_Update() {
		table = new JTable(new BookModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		table.addMouseListener(this);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(194, 61, 502, 256);
        add(jscp1);
	}
	
	
		 @Override
		 public void mouseClicked(MouseEvent e) {
		  int row = table.getSelectedRow();
		  int book_number = (Integer) table.getModel().getValueAt(row,1);
		  BookDao bDao = new BookDao();
		  
		  if(bDao.getBookRentYN(book_number) != 1) {
		Book_Update_Page update = new Book_Update_Page(book_number);
		  update.setVisible(true);
		  // 책 번호
		  }else {
			  JOptionPane.showMessageDialog(null, "해당 도서가 대여 중입니다.");
			  
		  }
		  
		}
		  @Override
		  public void mousePressed(MouseEvent e) {
		   // TODO Auto-generated method stub
		   
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
