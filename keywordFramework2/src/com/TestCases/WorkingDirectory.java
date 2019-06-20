package com.TestCases;

import java.io.File;

public class WorkingDirectory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path=System.getProperty("user.dir");
		
		System.out.println("File path Separator Char "+ File.pathSeparatorChar);
		System.out.println("current working directory is "+path);
		System.out.println("path Separator is "+ File.pathSeparator);
		System.out.println("Separator is "+ File.separator);
		System.out.println("Separator char is "+ File.separatorChar);


	}

}
