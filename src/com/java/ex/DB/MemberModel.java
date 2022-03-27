package com.java.ex.DB;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
public class MemberModel extends AbstractTableModel {
	Object[][] data;
	Object[] columnName;
	
	MemberDao mDao = new MemberDao();
	MemberDto mDto;
	ArrayList<String> title;
	ArrayList<MemberDto> list;
	
	public MemberModel() {
		// TODO Auto-generated constructor stub
		title = mDao.getMemberSearchColumn();
		columnName = title.toArray();
		int columnCount = title.size();
		
		list = mDao.getMemberSearch();
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		
		for (int index=0; index<rowCount; index++) {
			mDto = list.get(index);
			data[index][0] = mDto.getId();
			data[index][1] = mDto.getPw();
			data[index][2] = mDto.getName();
			data[index][3] = mDto.getEmail();
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
