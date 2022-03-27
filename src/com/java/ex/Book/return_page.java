package com.java.ex.Book;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.java.ex.DB.BookDao;
import com.java.ex.DB.RentDao;
import com.java.ex.DB.RentModel2;

public class return_page extends JPanel implements MouseListener {
	JTable table;
	String ids  = "0";
	public return_page(String id) {
		ids = id;
		table = new JTable(new RentModel2(id));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		table.addMouseListener(this);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(194, 61, 502, 256);
        add(jscp1);
	}
	
	 @Override
	 public void mouseClicked(MouseEvent e) {
	  int row = table.getSelectedRow();
	  int book_number = (int) table.getModel().getValueAt(row,3);
	  RentDao rDao = null;
	  BookDao bDao = null;

		Date return_date = new Date(); 
		Calendar cal = Calendar.getInstance();
	  try {
		  		rDao = new RentDao();
		  		bDao = new BookDao();
		  		
		  		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
				  cal.setTime(return_date); 
				  cal.add(Calendar.DATE, 0);
				  String dateStr = timeFormat.format(cal.getTime());
		  		if(rDao.getOverdue(ids) == 1) {
		  			JOptionPane.showMessageDialog(null , "제때제때 반납해주세요^^; ");
		  		}
		  		rDao.getRentReturn(dateStr,book_number);
				bDao.getBookReturn(book_number);
		  		
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		  if(rDao != null) {
				JOptionPane.showMessageDialog(null,book_number + "도서 반납 성공!");
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
