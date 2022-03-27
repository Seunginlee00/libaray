package com.java.ex.Book;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.java.ex.DB.BookDao;
import com.java.ex.DB.MemberDao;
import com.java.ex.DB.RentModel;

public class rental_status_page extends JPanel implements MouseListener {
	JTable table;
	String ids;
	public rental_status_page(String id) {
		ids = id;
		table = new JTable(new RentModel(id));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		table.addMouseListener(this);
		setLayout(null);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(34, 5, 612, 428);
        add(jscp1);
	}
	
	 @Override
	 public void mouseClicked(MouseEvent e) {
		  BookDao bDao = null;
		  int row = table.getSelectedRow();
		  int booknumber = (int) table.getModel().getValueAt(row,3);
		  String bookname = (String) table.getModel().getValueAt(row,4);
		  try {
			  bDao = new BookDao();
			  int count = bDao.getBookCount(booknumber);
			  JOptionPane.showMessageDialog(null, bookname + "도서의 빌린 횟수는" +count+ "번 입니다.");
		  }catch (Exception e11) {
			// TODO: handle exception
			  e11.printStackTrace();
			   
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
