package BusinessFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import LIB.Common;

public class Login {
	Common browser;

	// constructor with one argument.
	public Login(Common br) {
		browser = br;
	}

	// initialization and assigning of properties file
	Properties locators = new Properties();
	File locfile = new File(
			"C:\\Users\\Naveen\\Downloads\\HRM_FinalFramework\\src\\main\\java\\ElementLocators\\Login.properties");

	// Setup for properties file
	public void setUp() throws Exception {
		locators.load(new FileInputStream(locfile));
	}

	// login to application (method for login to application)
	public void loginToapp(String username, String password) throws Exception {
		String usernameloc = locators.getProperty("username.textfield.loc");
		String passwordloc = locators.getProperty("password.textfield.loc");
		String loginbtnloc = locators.getProperty("login.button.loc");
		
		 String[] valueToWrite = {"qaplanet2","user2"};
		// Set the Excelfile
		DataInExcel.setExcelFile();	
		
		// Get the first row 		
		String userNameFromExcel = DataInExcel.getExcelData(1, 0);
		// get the second row
		String passwordFromExcel = DataInExcel.getExcelData(1, 1);
	//	DataInExcel.setCellData("Sheet1",valueToWrite);
		// verify username
		browser.verifyElementPresent("name", usernameloc);
		browser.ClearTextField("name", usernameloc);
		browser.sendKeys("name", usernameloc, userNameFromExcel);
		// verify password
		browser.verifyElementPresent("xpath", passwordloc);
		browser.ClearTextField("xpath", passwordloc);
		browser.sendKeys("xpath", passwordloc, passwordFromExcel);
		browser.sleepThread(1000);
		browser.verifyElementPresent("xpath", loginbtnloc);
		browser.click("xpath", loginbtnloc);
		browser.waitForPageToLoad();
	}

	// Method for logout
	public void logout() {
		// select frame
		browser.selectFrameDefault();
		browser.click("linkText", "Logout");
	}

	public void addEmp(String firstname, String lastname) throws Exception {
		// click on add emp
		browser.startAction();
		// selecting frame
		browser.selectFrameById("rightMenu");
		// click on add button
		browser.click("xpath", "//*[@id='standardView']/div[3]/div[1]/input[1]");
		// Wait until PIM : Add Employee displayed
		browser.waitUntilElementPresent("//*[@id='frmEmp']/div/div[1]/div[2]/div[1]/h2");
		// Verify PIM : Add Employee text
		browser.verifyText("xpath", "//*[@id='frmEmp']/div/div[1]/div[2]/div[1]/h2", "PIM : Add Employee");
		// enter first and last name
		browser.sendKeys("id", "txtEmpLastName", lastname);
		browser.sendKeys("name", "txtEmpFirstName", firstname);
		// click on save button
		browser.click("id", "btnEdit");
		// wait for Personal Details
		browser.waitUntilElementPresent("//*[@id='personal']/div[1]/div[2]/div[1]/h2");
	}

	// Add location
	public void addlocation() throws Exception {
		// select frame
		browser.selectFrameDefault();
		// Focus on Admin tab
		Thread.sleep(6000);
		browser.MouseOverToElement("id", "admin");

		// Focus on Company info
		browser.MouseOverToElement("xpath", "//li[@id='admin']/ul/li/a");

		// Click on Locations
		browser.click("xpath", "//li[@id='admin']/ul/li/ul/li[2]/a");

		Thread.sleep(2000);
		// Swith to the frame //
		browser.selectFrameById("rightMenu");

		Thread.sleep(2000);

		// Verify Company Info : Locations page is displayed
		browser.waitUntilElementPresent("//div[@class='mainHeading']/h2");
		browser.verifyText("xpath", "//div[@class='mainHeading']/h2", "Navigated to Locations page");

		// Click on Add
		browser.click("xpath", "//div[@class='actionbuttons']/input[1]");

		// Keep all fields as blank and click on save
		browser.click("id", "editBtn");

		// wait for Please correct the following alert
		if (browser.verifyPopup(
				"Please correct the following\n\n	- Location Name has to be specified\n	- Country should be selected!\n	- Address should be specified\n	- Zip Code should be specified\n")) {
			throw new Exception("Failed to handle the popup");
		}
	}

	public void verifyItemInEmpList(String firstname, String lastname) throws Exception {
		// select frame
		browser.selectFrameDefault();
		// click on emp list
		browser.click("linkText", "PIM");
		// select frame
		browser.selectFrameById("rightMenu");
		// Wait for Employee info element
		browser.waitUntilElementPresent("//*[@id='standardView']/div[1]/h2");
		// verify Employee information
		browser.verifyText("xpath", "//*[@id='standardView']/div[1]/h2", "Employee Information");
		// wait for 2 sec
		browser.sleepThread(2000);
		String itemname = firstname + " " + lastname;
		browser.verifyElementInTable("//table[@class='data-table']/tbody/tr/td[3]", itemname);
	}
}
