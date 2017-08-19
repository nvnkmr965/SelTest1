package BusinessFunctions;
import LIB.Common;
public class Admin {
	Common browser;

	// constructor with one argument.
	public Admin(Common br) {
		browser = br;
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

		// Swith to the frame
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
		if (!browser.verifyPopup(
				"Please correct the following\n\n	- Location Name has to be specified\n	- Country should be selected!\n	- Address should be specified\n	- Zip Code should be specified\n")) {
			throw new Exception("Failed to handle the popup");
		}

		// Enter Name
		browser.sendKeys("id", "txtLocDescription", "Wipro");

		// Select Country
		browser.selectCountry();

		// Enter Address
		browser.sendKeys("id", "txtAddress", "Hyderabad");

		// Enter zipcode
		browser.sendKeys("id", "txtZIP", "50032");

		// Click Save
		browser.click("id", "editBtn");

		Thread.sleep(3000);

		// Verification: It verifies only Location Name column details
		if (browser.verifyLocationInTable("//table[@class='data-table']/tbody/tr/td[3]", "Wipro")) {
			System.out.println("Added the loacation");
		} else {
			throw new Exception("Faile to add the location");
		}

		// Verify Location in the table. It verifies all the cells in the table for the given string
		if (browser.verifyElementInTable("//table[@class='data-table']/tbody/tr/td", "Wipro")) {
			System.out.println("Successfully added the location");
		} else {
			throw new Exception("Failed to add the location");
		}
	}
	
	// Company Scructure
	public void CompanyScructure() throws Exception {
		// select frame
		browser.selectFrameDefault();
		// Focus on Admin tab
		Thread.sleep(6000);
		browser.MouseOverToElement("id", "admin");

		// Focus on Company info
		browser.MouseOverToElement("xpath", "//li[@id='admin']/ul/li/a");

		// Click on Locations
		browser.click("xpath", "//li[@id='admin']/ul/li/ul/li[2]/a");		
	}

	public static void editLocation() {
		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//table[@class='data-table']/tbody/tr")));
		 * 
		 * int totalLocations =
		 * driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")
		 * ).size();
		 * 
		 * System.out.println(totalLocations);
		 * 
		 * int locationsDetails = driver.findElements(By.xpath(
		 * "//table[@class='data-table']/tbody/tr[1]/td")).size();
		 * 
		 * 
		 * System.out.println(locationsDetails);
		 * 
		 * boolean clicked = false; for (int i = 1; i < totalLocations; i++) {
		 * for (int j = 2; j < locationsDetails; j++) { String ActualLocation =
		 * driver .findElement(By.xpath("//table[@class='data-table']/tbody/tr["
		 * + i + "]/td[" + j + "]/a")) .getText();
		 * 
		 * System.out.println(ActualLocation); if
		 * (ActualLocation.equals("Wipro")) { System.out.println(i);
		 * System.out.println(j); Thread.sleep(2000);
		 * driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["
		 * + i + "]/td[" + j + "]/a")) .click();
		 * 
		 * clicked = true; break; } }
		 * 
		 * if (clicked) { break; } }
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "editBtn"))); driver.findElement(By.id("editBtn")).click();
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "txtLocDescription")));
		 * driver.findElement(By.id("txtLocDescription")).clear();
		 * driver.findElement(By.id("txtLocDescription")).sendKeys("Wipro Tech"
		 * );
		 * 
		 * driver.findElement(By.id("editBtn")).click();
		 * 
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//table[@class='data-table']/tbody/tr"))); totalLocations =
		 * driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")
		 * ).size(); System.out.println(totalLocations); locationsDetails =
		 * driver.findElements(By.xpath(
		 * "//table[@class='data-table']/tbody/tr[1]/td")).size();
		 * System.out.println(locationsDetails);
		 * 
		 * clicked = false; for (int i = 1; i < totalLocations; i++) { for (int
		 * j = 2; j < locationsDetails; j++) { String UpdatedLocation = driver
		 * .findElement(By.xpath("//table[@class='data-table']/tbody/tr[" + i +
		 * "]/td[" + j + "]/a")) .getText();
		 * System.out.println(UpdatedLocation); if (UpdatedLocation.equals(
		 * "Wipro Tech")) { System.out.println(i); System.out.println(j);
		 * Thread.sleep(2000);
		 * driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["
		 * + i + "]/td[" + j + "]/a")); System.out.println(
		 * "Updated location is " + UpdatedLocation);
		 * driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["
		 * + i + "]/td[1]/input")) .click();
		 * driver.findElement(By.xpath("//div[@class='actionbuttons']/input[2]")
		 * ).click(); // Deletion might affect Company Hierarchy. If the
		 * Location // has associations deletion may fail. Do you want to delete
		 * // ? // wait for Please correct the following alert popup =
		 * wait.until(ExpectedConditions.alertIsPresent());
		 * System.out.println(popup.getText()); if (popup.getText().equals(
		 * "Deletion might affect Company Hierarchy. If the Location has associations deletion may fail. Do you want to delete ?"
		 * )) { popup.accept(); } else { throw new Exception(
		 * "Failed to get popup"); }
		 * 
		 * if (wait.until(ExpectedConditions.textToBePresentInElementLocated(By.
		 * className("success"), "Successfully Deleted"))) { System.out.println(
		 * "Deleted the location successfuly"); } else { throw new Exception(
		 * "Failed to delete the location"); } clicked = true;
		 * 
		 * break; } } if (clicked) { break; } }
		 * 
		 * System.out.println("Success"); }
		 */
	}
}
