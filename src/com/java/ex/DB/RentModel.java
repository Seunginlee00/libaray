package com.java.ex.DB;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
public class RentModel extends AbstractTableModel {
	Object[][] data;
	Object[] columnName;
	RentDao rDao = new RentDao();
	RentDto rDto;
	ArrayList<String> title;
	ArrayList<RentDto> list;
	
	public RentModel(String id) {
		// TODO Auto-generated constructor stub
		title = rDao.getClientSearchColumn(id);
		columnName = title.toArray();
		int columnCount = title.size();
		
		list = rDao.getClientSearch(id);
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		
		for (int index=0; index<rowCount; index++) {
			rDto = list.get(index);
			data[index][0] = rDto.getRent_date();
			data[index][1] = rDto.getReturn_date();
			data[index][2] = rDto.getRent_return_date();
			data[index][3] = rDto.getBook_number();
			data[index][4] = rDto.getBook_name();
			data[index][5] = rDto.getRent_return_yn();
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
