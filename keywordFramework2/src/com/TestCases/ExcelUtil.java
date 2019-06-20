package com.TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtil {
	
	static FileInputStream fs=null;
	static Workbook wb=null;
	static Sheet sh=null;
	static int NoRows=0;
	static int NoCols=0;
	static String [][] MenuName=null;
	
	public static int getRowCount()
	{
		
		return NoRows;
	}
	
	public static int getColCount()
	{
		
		return NoCols;
	}
	 public static String[][] getExcelData(String Fname,String Sname)
	  {
		 
		 
		 try {
			 
			  fs=new FileInputStream(Fname);
			  wb= Workbook.getWorkbook(fs);
			  sh= wb.getSheet(Sname);
			  
			  NoCols=sh.getColumns();
			  NoRows=sh.getRows();
			  
			  //System.out.println("No. of columns are "+ NoCols);
			  //System.out.println("No. of rows are "+ NoRows);
			  
			  
			  MenuName=new String[NoRows][NoCols];
			  
			  for(int i=0;i<NoRows;i++)
			  {
				  MenuName[i][0]=sh.getCell(0, i).getContents();
				  		  
			  }
			  
			  System.out.println("*********** Menu From Excel sheet are ************");
			  for(int i=0;i<NoRows;i++)
			  {
				 System.out.println(MenuName[i][0]);
				  		  
			  }
			  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JXLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return MenuName;
	  }


}
