package com.java.ex.Book_Use;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.java.ex.DB.BookModel;
import com.java.ex.DB.MemberDao;
import com.java.ex.DB.MemberModel;
import com.java.ex.DB.RentDao;
import com.java.ex.UI.Book_Update_Page;

public class Client_Status extends JPanel implements MouseListener{
	JTable table;

	public Client_Status() {
		table = new JTable(new MemberModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		table.addMouseListener(this);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(194, 61, 502, 256);
        add(jscp1);
        
	}
	

	 @Override
	 public void mouseClicked(MouseEvent e) {
		 
	  int row = table.getSelectedRow();
	  int result = 0;
	  String id = (String) table.getModel().getValueAt(row,0);
	  
	  
	  MemberDao mDao = null;
	  RentDao rDao = null;
	  try {
		  rDao = new RentDao();
		  mDao = new MemberDao();
		  
		  result = rDao.getOverdue(id);
		  if(result == 1) {
			  // 못빌리게 처리.. 어케 하냐고요~~~ 썅~~~ 
		  }
		  
		  int count = mDao.getClientCount(id);
		  JOptionPane.showMessageDialog(null, id + "님이 빌린 도서의 수는" +count+ "권 입니다.");
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