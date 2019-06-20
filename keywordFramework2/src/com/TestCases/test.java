package com.TestCases;

import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriver driver=new FirefoxDriver();
		driver.get("file:///D:/Selenium/JBK%20Offline/index.html");
		//List<WebElement> li=  driver.findElements(By.xpath("//*[@id='cssmenu']//descendant::a"));
		
		java.util.List<WebElement> links = driver.findElements(By.xpath("//*[@id='cssmenu']//descendant::a"));
		//((WebElement) links).click();
		
		System.out.println(links.size());
		
		
	}

}
