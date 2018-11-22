package assessemnt.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;


import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.ClearElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task2 {
	
	public static void main(String[] args) {
		
		
	}
	//Instantiate webdriver
	 WebDriver driver = new ChromeDriver();
	 XSSFWorkbook wb;
	XSSFSheet sheet1;
	
	//navigate to the page given
	@Test(priority=1)
	public void Navigate()
	
	{
		//maximize the page after navigating to it
		   driver.manage().window().maximize();
		   //time to wait for the page to open
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//page that you have to navigate to
			driver.navigate().to("http://www.way2automation.com/angularjs-protractor/webtables/");
	}
	@Test(priority=2)
	//validating that we are on the list table
public void Validate() {
		
		
		String title="http://www.way2automation.com/angularjs-protractor/webtables/";

		
		if(title.equalsIgnoreCase(title))
		{
		   System.out.println("This is user list table");
		}
		else
		{
		   System.out.println("Not on user list table");
		}
	}
	//Clicking add user button
	@Test(priority=3)
	public void Click() {
		driver.findElement(By.cssSelector("body > table > thead > tr:nth-child(2) > td > button")).click();
	}
	
	@Test(priority=4)
	//method to add users to the web table
	public void AddUser() throws IOException, InterruptedException {
		//array to get all the users 
		ArrayList<String> FirstName = PassData(0);
		ArrayList<String> LastName = PassData(1);
		ArrayList<String> UserName = PassData(2);
		ArrayList<String> Password = PassData(3);
		ArrayList<String> Customer = PassData(4);
		ArrayList<String> Role = PassData(5);
		ArrayList<String> Email = PassData(6);
		ArrayList<String> Cell = PassData(7);
		//Loop will iterate on all textboxes enetering the required info.
		for(int i=0;i<FirstName.size();i++) {
			
			EnsureUserUniqueANDuserUnique(UserName.get(i));
			
			//clearing the text box before sending information
			driver.findElement(By.name("FirstName")).clear();
			//Sending information to the first name textbox
			driver.findElement(By.name("FirstName")).sendKeys(FirstName.get(i));
			//clearing the text box before sending information
			driver.findElement(By.name("LastName")).clear();
			//Sending information to the last name textbox
			driver.findElement(By.name("LastName")).sendKeys(LastName.get(i));
			//clearing the text box before sending information
			driver.findElement(By.name("UserName")).clear();
			//Sending information to the username textbox
			driver.findElement(By.name("UserName")).sendKeys(UserName.get(i));
			//clearing the text box before sending information
			driver.findElement(By.name("Password")).clear();
			//Sending information to the password textbox
			driver.findElement(By.name("Password")).sendKeys(Password.get(i));
			
		//Click on radio button if it contains 
			if (Customer.contains("Company AAA")) {
	             WebElement RadioButton = (driver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > label:nth-child(1)")));
	         RadioButton.click();
	             
	         }
	         else {
	        	 
	             WebElement RadioButton = (driver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > label:nth-child(2)")));
	             RadioButton.click();
	         }
			//selecting the role whether its admin or a customer
			Select dropdown = new Select(driver.findElement(By.name("RoleId")));
		   dropdown.selectByVisibleText(Role.get(i));
			
		 //clearing the text box before sending information
			driver.findElement(By.name("Email")).clear();
			//Sending information to the email textbox
			driver.findElement(By.name("Email")).sendKeys(Email.get(i));
			//clearing the text box before sending information 
			driver.findElement(By.name("Mobilephone")).clear();
			//Sending information to the cellphone textbox
			driver.findElement(By.name("Mobilephone")).sendKeys(Cell.get(i));
			//the save button for saving the users on the table
			driver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-footer > button.btn.btn-success")).click();
			//Click add user button to get the next user
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("body > table > thead > tr:nth-child(2) > td > button")).click();
		}
		//Button to close 
		driver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-footer > button.btn.btn-danger")).click();
		Thread.sleep(5000);
		}
		
	
	//This method will fetch test data from excel file
	@Test(priority=5)                                                         
	public ArrayList<String> PassData(int columnNo) throws IOException {
		//Fetch the file where the excel file is located
		File excelfile = new File("D:\\Param\\Credentials.xlsx");
		//fetches the file
		FileInputStream fls = new FileInputStream(excelfile);
		//Create an XSSF Workbook object for xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(fls);
		//get first sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//iterate on rows
		Iterator<Row> rowit = sheet.iterator();
		rowit.next();
		//array for all names
		ArrayList<String> list = new ArrayList<String>();
		while(rowit.hasNext()) {
			//get all the values from excel file
				list.add(rowit.next().getCell(columnNo).getStringCellValue());
			
			
		}
		//print all the users from excel file
		System.out.println("******"+list+"******");
		
		return list;

		
		
		}
	
	
	//Method that checks if the user name is unique and it ensures that the user is added
	
	public void EnsureUserUniqueANDuserUnique(String UserName) {
	
	//array to locate the username on the web
			int tr[]= new int [7];
			System.out.println(UserName);
			
			
			//looping the names
			for(int i=1; i<tr.length;i++ ) {
				
				tr[i]=i;
				//get the xpath of username table
				String add = driver.findElement(By.xpath("/html/body/table/tbody/tr["+tr[i]+"]/td[3]")).getText();
				
				//ensure the user is unique
			if(UserName.contains(add)) {
				//if the username is the same it will print this
				System.out.println("The user has been added already");
			
			}

			}
		  //if its a new user it will print successfull
			System.out.println("The user is added successfully");

	  }
			
	
}
