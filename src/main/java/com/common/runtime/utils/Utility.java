package com.common.runtime.utils;

import java.util.ArrayList;

import com.common.runtime.configure.SelectTestData;

public class Utility {

	static Xls_Reader read = new Xls_Reader(SelectTestData.gstrspringerDataSheet);
	
	
	public static ArrayList<Object[]> getSearchData()
	{
		ArrayList<Object[]> mydata= new ArrayList<Object[]>();
		for(int rowNum=2;rowNum<=read.getRowCount("springer");rowNum++)
		{
			String searchtext = read.getCellData("springer", "searchtext", rowNum);
			
			Object ob[]={searchtext};
			mydata.add(ob);
		}
		return mydata;
	}
	
}
