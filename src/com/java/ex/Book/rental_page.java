package com.java.ex.Book;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.java.ex.DB.BookDao;
import com.java.ex.DB.BookModel;
import com.java.ex.DB.BookModel3;
import com.java.ex.DB.MemberDao;
import com.java.ex.DB.RentDao;
import com.java.ex.DB.RentDto;

import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;

public class rental_page extends JPanel implements MouseListener{
	JTable table;
	JTable table2;
	int returns = 0;
	private JTextField searchField;
	BookModel bm1 = new BookModel();
	private String ids;
	private JButton searchBtn;
	public rental_page(String id) {
		
		ids = id;
		setLayout(null);
		
		
		table = new JTable(new BookModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		table.addMouseListener(this);
		JScrollPane jscp1 = new JScrollPane(table);
		jscp1.setBounds(229, 61, 527, 256);
        add(jscp1);
		
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(25, 91, 179, 187);
		add(searchPanel);
		searchPanel.setLayout(null);
		
		
		
		JComboBox<String> searchBox = new JComboBox<String>();
		searchBox.setModel(new DefaultComboBoxModel<String>(new String[] {"도서명","저자명","출판사"}));
		searchBox.setBounds(31, 22, 74, 23);
		searchPanel.add(searchBox);
		
		
		searchBtn = new JButton("검색");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					if(searchBox.getSelectedItem().toString() == "도서명") {
						// 일단 검색은 됨... 
						table2 = new JTable(new BookModel3(searchField.getText()));
						table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
						//table2.addFocusListener(this);
						JScrollPane jscp2 = new JScrollPane(table2);
						jscp2.setBounds(194, 61, 502, 256);
				        add(jscp2);
					}
					
					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}	
			
		});
		
		
		

		
		
		searchBtn.setBounds(12, 107, 68, 23);
		searchPanel.add(searchBtn);
		
		searchField = new JTextField();
		searchField.setBounds(12, 62, 133, 21);
		searchPanel.add(searchField);
		searchField.setColumns(10);
		
		JButton resetBtn = new JButton("초기화");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table = new JTable(new BookModel());
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
				//table.addMouseListener(this);
				JScrollPane jscp1 = new JScrollPane(table);
				jscp1.setBounds(194, 61, 502, 256);
		        add(jscp1);
			}
		});
		resetBtn.setBounds(31, 140, 97, 23);
		searchPanel.add(resetBtn);
		
		JButton rentBtn = new JButton("대여");
		rentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					int book_number = (int) table2.getModel().getValueAt(0,1);
					RentDto rDto = null;
					RentDao rDao = new RentDao();
					BookDao bDao = new BookDao();
					MemberDao mDao = new MemberDao();
					Date rental_date = new Date();
					Date return_date = new Date(); 
					Calendar cal = Calendar.getInstance();
					
					 try {
						 //대출날
						  SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
						  cal.setTime(rental_date); 
						  cal.add(Calendar.DATE, 0);
						  String dateStr = timeFormat.format(cal.getTime()); 
						 
						 //반납날
						  SimpleDateFormat timeFormat2 = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
						  cal.setTime(return_date); 
						  cal.add(Calendar.DATE, 14);
						  String dateStr2 = timeFormat2.format(cal.getTime());
							 // 렌탈시 정보를 보냄
						    
							
						    // 렌탈이 안되는 상황
						    // 1. 연체 -> 반납 다하면 다시 대여 가능 
						    // 2. 5권이상 쳐 빌린 상황  -> 다시 반납해서 숫자가 줄면 ok   mDao.getRentCount(ids) == -1
						  	// 3. 이미 빌린 경우  bDao.getBookRentYN(book_number) => 1
						    if(bDao.getBookRentYN(book_number) == 0 && mDao.getRentCount(ids) == -1 && ) {
						    	// 렌탈
						    	rDto = new RentDto(dateStr, null, dateStr2, book_number, searchField.getText(), ids,"N");
						    	rDao.getRent(rDto);
						    	// 
							    bDao.setBookCount(book_number);
							    // 렌탈 정보 기록 
								rDao.getBookRent(book_number);
								// 도서카운트 수  
								mDao.setRentCount(ids);
						    }
						    
						  
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					  
					 
			}
		});
		rentBtn.setBounds(92, 107, 64, 23);
		searchPanel.add(rentBtn);
		
	

	}
	
 
	 @Override
	 public void mouseClicked(MouseEvent e) {
		
		int row = table.getSelectedRow();
		int book_number = (int) table.getModel().getValueAt(row,1);
		String bookname = (String) table.getModel().getValueAt(row,2);
		RentDao rDao =  new RentDao();
		RentDto rDto =  null;
		BookDao bDao = new BookDao();

		MemberDao mDao = new MemberDao();
		Date rental_date = new Date();
		Date return_date = new Date(); 
		Calendar cal = Calendar.getInstance();
		
		 try {
			 //대출날
			  SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
			  cal.setTime(rental_date); 
			  cal.add(Calendar.DATE, 0);
			  String dateStr = timeFormat.format(cal.getTime()); 
			 
			 //반납날
			  SimpleDateFormat timeFormat2 = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
			  cal.setTime(return_date); 
			  cal.add(Calendar.DATE, 14);
			  String dateStr2 = timeFormat2.format(cal.getTime());

			  int result_over = rDao.getOverdue(ids);
			  int result_return = rDao.getOverdueReturn(ids);
			  if(bDao.getBookRentYN(book_number) != 1) {    
				  if(result_over == 1) {
			    rDto = new RentDto(dateStr, null, dateStr2, book_number, bookname, ids,"N");	
				rDao.getRent(rDto);
				rDao.getBookRent(book_number);
				bDao.setBookCount(book_number);
				mDao.setClientCount(ids);
				
				  } else {
					  
					  JOptionPane.showMessageDialog(null, "당신... 연체네요?");
				
				 if(rDao != null && ids != null) {
					 if(result_over == 1) {
						JOptionPane.showMessageDialog(null,book_number + "도서 대여 성공!");
					 }
					}
				  else {
					  JOptionPane.showMessageDialog(null, "로그인 먼저 진행해 주세요");
				  }
				
					  
				  }
				
		 	}else {
		 		JOptionPane.showMessageDialog(null, "해당 도서가 대여 중입니당...");
		 	}
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
