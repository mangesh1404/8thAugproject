package com.jbk;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelRead {

	
	@Test
	public void test01() throws Exception {
		
		FileInputStream fis = new FileInputStream("Test.xls");
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sh = wb.getSheet("login");
		
		int rows = sh.getRows();
		int cols = sh.getColumns();
		
		for(int i=0;i<rows;i++) {// for rows
			for(int j=0;j<cols;j++) {// for cols
				
				Cell c = sh.getCell(j, i);
				String data = c.getContents();
				System.out.print(data+"  ");
			}
			System.out.println();
		}
			
	}
	
	@Test(dataProvider="getLogindata")
	public void loginWithDp(String uname, String pass, String title) {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/Administrator/Desktop/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).click();
		
		Assert.assertEquals(driver.getTitle(), title);
		
	}
	@DataProvider
	public Object[][] getLogindata() throws Exception { 
		FileInputStream fis = new FileInputStream("Test.xls");
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sh = wb.getSheet("login");
		int rows = sh.getRows();// 3
		int cols = sh.getColumns();// 2
		String[][] data  = new String[rows][cols];
		for (int i = 0; i < rows; i++) {// for rows
			for (int j = 0; j < cols; j++) {// for cols
				Cell c = sh.getCell(j, i);
				String value = c.getContents();
				data[i][j] = value;
			}
		}
		return data;
	}
	
}
