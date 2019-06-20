package com.TestCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TestUtil {

	public static boolean CompLinkNames(String str1, String str2)
	{
		if(str1.equals(str2))
				return true;
		else
			 return false;
	}

	public static boolean compLinkSequence(ArrayList<String>array2,String []url,int count)
	{
		 
		//if(Arrays.equals(array2, url))//use collections prepferebly
		/*if(array2.equals(url))//use collections prepferebly
		{
			System.out.println("*********** Link sequence on "+ url[count]+" page is same ***************");
			return true;
		}
		else
		{
			System.out.println("*********** Link sequence on "+ url[count]+" page is not same ***************");
			return false;

		}*/
		HashSet<String> temp=new HashSet<String>();
		//temp.addAll(array2);
		for(String str:array2)
		{
			if(!temp.add(str))
			{
				System.out.println("Link "+str+" is duplicate");
				DataProviderReadExcel.log1.info("Link "+str+" is duplicate");
				return false;
			}
		}
		
		HashSet<String > LinkDiff = new HashSet<String>();
		HashSet<String> temp2=new HashSet<>();
		//temp2.addAll((HashSet)url);
		for(String str: url)
		{
			temp2.add(str);
		}
		
		for(String str:array2 )
		{
			if(temp2.add(str))
			{
				LinkDiff.add(str);
			}
		}
		
		if(LinkDiff.size()>0)
		{
			for(String str:LinkDiff)
			{
				System.out.println("Link '"+ str+"' does not math with the ExcelData");
				DataProviderReadExcel.log1.warn("Link '"+ str+"' does not math with the ExcelData");
			}
			return false;
		}
		
		return true;
	}
	
	public static boolean checkLinkCount(ArrayList<String> stringarray,int noLinks)
	{
		if(stringarray.size()==ExcelUtil.getRowCount())
		{
			return true;
		}
		else 
			return false;
	}
	
	public static void NavigateToNewPage(int NoLinks, String Link)
	{
	
		
		
	}
	
}
