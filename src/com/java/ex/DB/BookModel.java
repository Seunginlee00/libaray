package com.java.ex.DB;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
public class BookModel extends AbstractTableModel {
	String[] header = {"총류", "책번호","책이름","출판사","저자명","빌린여부"};
	Object[][] data;
	Object[] columnName;
	
	BookDao bDao = new BookDao();
	BookDto bDto;
	ArrayList<String> title;
	ArrayList<BookDto> list;
	
	public BookModel() {
		// TODO Auto-generated constructor stub
		title = bDao.getBookSearchColumn();
		columnName = title.toArray();
		int columnCount = title.size();
		
		list = bDao.getBookSearch();
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		
		for (int index=0; index<rowCount; index++) {
			bDto = list.get(index);
			data[index][0] = bDto.getGeneral();
			data[index][1] = bDto.getBook_number();
			data[index][2] = bDto.getBook_name();
			data[index][3] = bDto.getBook_publisher();
			data[index][4] = bDto.getBook_author_name();
			data[index][5] = bDto.getBook_rent_yn();
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		if(columnName == null)
			return 0;
		else
			return columnName.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(data == null)
			return 0;
		else
			return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return (String) columnName[column];
	}

}
