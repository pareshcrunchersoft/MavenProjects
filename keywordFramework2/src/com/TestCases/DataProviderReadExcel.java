package com.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class DataProviderReadExcel {

	WebDriver driver = null;
	String[][] MenuName = null;
	String[] url = null;
	public static int count = 0;
	int noLinks = 0;
	static Logger log1 = null;

	// public String[] getLinkNames()
	
	public static void handleAlert(WebDriver driver){
        if(isAlertPresent(driver)){
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            //alert.dismiss();
        	
        	driver.switchTo().alert().accept();
        	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        	driver.switchTo().alert().accept();
        	
          }
        }
	
	
        public static boolean isAlertPresent(WebDriver driver){
        try{
            driver.switchTo().alert();
            return true;
            }catch(NoAlertPresentException ex){
                  return false;
            }
        }
	public ArrayList<String> getLinkNames() {
		System.out.println("Clicking the page '" + driver.getTitle()+"'");
		log1.info("Clicking the page '" + driver.getTitle()+"'");
		WebElement weDiv = driver.findElement(By.xpath("//*[@id='cssmenu']"));
		WebElement Link = null;

		List<WebElement> li = weDiv.findElements(By.tagName("a"));

		Iterator<WebElement> it1 = li.iterator();

		// String[] Linkarray= new String[li.size()];
		ArrayList<String> Linkarray = new ArrayList<String>(li.size());

		int i = 0;
		System.out.println("Links on " + driver.getTitle() + " page are as follows:");
		log1.info("Links on " + driver.getTitle() + " page are as follows:");

		while (it1.hasNext()) {
			Link = it1.next();

			// Linkarray[i]=Link.getText();
			Linkarray.add(Link.getText());
			System.out.println(Link.getText());
			log1.info("********* " + (i + 1) + "." + Link.getText() + " ********");
			i++;
		}

		return Linkarray;
	}

	@BeforeTest
	public void getLinkName() {

		WebElement weDiv = driver.findElement(By.xpath("//*[@id='cssmenu']"));
		WebElement Link = null;
		java.util.List<WebElement> li = weDiv.findElements(By.tagName("a"));

		Iterator<WebElement> it1 = li.iterator();
		url = new String[li.size()];

		int i = 0;

		while (it1.hasNext()) {
			Link = it1.next();
			url[i] = Link.getText();
			i++;
		}

	}

	// @Test(priority=2)
	public void checkActiveLinks(ArrayList<String> array2) {
		String currURL = null;
		currURL = driver.getCurrentUrl();
		System.out.println("******** checking active links *************");
		log1.info("******** checking active links *************");
		System.out.println("");
		for (String LinkName : array2) {
			try {
				// driver.findElement(By.partialLinkText(array2[i])).click();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//				currURL = driver.getCurrentUrl();
				try
				{
					//handleAlert(driver);
				driver.findElement(By.xpath("//a[contains(text(),'" + LinkName + "')]")).click();
				   handleAlert(driver);
				}
				catch (UnhandledAlertException e) {
	                 System.err.println("Caught UnhandledAlertException: ");
	                 Alert alert = driver.switchTo().alert();
	                 alert.accept();
				}
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// System.out.println(array2[i]+" Link is not active");
				log1.error("######## ERROR #############");
				log1.error("###" + LinkName + " Link is not active ###");
				e.printStackTrace();
			}

			// driver.navigate().back();
			driver.navigate().to(currURL);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		}
	}

	@Test(priority = 1, dataProvider = "dp")
	public void TestCheckLinks(String s, String s1) {

		WebElement weLink = null;
		String currURL=null;
		// int noLinks=Integer.parseInt(s1);
		// System.out.println("********************************************");
		log1.info("");
		log1.info("********************************************");
		System.out.println("Test data from data provider is " + s);
		log1.info("Test data from data provider is '" + s + "'");

		try {

			currURL = driver.getCurrentUrl();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			WebElement weDiv = driver.findElement(By.xpath("//*[@id='cssmenu']"));

			if (TestUtil.CompLinkNames(s, url[count])) {

				weLink = weDiv.findElement(By.xpath("//a[contains(text(),'" + s + "')]"));
				System.out.println(weLink.getText().toString());

				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				try {
					handleAlert(driver);
					weLink.click();
					handleAlert(driver);
				} catch (UnhandledAlertException e) {
	                 System.err.println("Caught UnhandledAlertException: ");
	                 Alert alert = driver.switchTo().alert();
	                 alert.accept();
				}
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

				ArrayList<String> array2 = getLinkNames();

				boolean flag2 = TestUtil.checkLinkCount(array2, noLinks);

				if (flag2 == true) {
					System.out.println("Link count is same");// how much
					log1.info("Link count is same on " + s + " page");
				} 
				else
				{
					// System.out.println("Link count is "+ array2.length +",
					// which does not match with the TestDataCount");
					log1.warn("Link count is " + array2.size() + ", which does not match with the TestDataCount");
				}
				checkActiveLinks(array2);
				TestUtil.compLinkSequence(array2, url, count);
				driver.navigate().to(currURL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("In TestCheckLinks Function");
			System.out.println(weLink.getText() + "is not working");
			e.printStackTrace();
		}
		count++;
	}

	@DataProvider
	public Object[][] dp() {

		// Object[][] arrayObject =
		// ExcelUtil.getExcelData("D:\\worksp1\\keywordFramework2\\src\\com\\excel\\TestData1.xls","Sheet1");
		Object[][] arrayObject = ExcelUtil
				.getExcelData(System.getProperty("user.dir") + "\\src\\com\\excel\\TestData1.xls", "Sheet1");
		return arrayObject;
	}

	@BeforeTest
	public void beforeTest() {
	}

	@BeforeSuite
	public void Setup() {
		log1 = Logger.getLogger("PareshLogger");
		// PropertyConfigurator.configure("D:\\worksp1\\keywordFramework2\\src\\com\\Report\\log4j.properties");
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\com\\Report\\log4j.properties");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("file:///D:/Selenium/JBK%20Offline/index.html");
	}

	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}

}
