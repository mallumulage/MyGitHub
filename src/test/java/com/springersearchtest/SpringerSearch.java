package com.springersearchtest;

import java.util.ArrayList;



import java.util.Iterator;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.runtime.components.Log4j;
import com.common.runtime.components.ObjectRepositry;
import com.common.runtime.utils.GenericElements;
import com.common.runtime.utils.Utility;


public class SpringerSearch extends GenericElements {

	Logger logger = Logger.getLogger(SpringerSearch.class);
	@BeforeMethod
		public void setup()
		{
			initialization();
		}
		@DataProvider
		public Iterator<Object[]> getTestData()
		{
			ArrayList<Object[]>testdata=Utility.getSearchData();
			return testdata.iterator();
		}
	
		@Test(priority=1,dataProvider="getTestData")
		public void searchResult(String searchtext)
		{
			
			Log4j.log();
			inputText(ObjectRepositry.searchbox, searchtext);
			clickElement(ObjectRepositry.searchbutton);
			String result=getText(ObjectRepositry.result);
			System.out.println(result);
			
		}
		
		@AfterMethod
		public void tearDown()
		{
			close();
		}
		
}
