package TestScripts;

import org.testng.annotations.Test;

import BusinessFunctions.Admin;
import BusinessFunctions.Login;

public class BrowsePhoto extends RootTest {
	public static Login login = new Login(brow);
	public static Admin admin = new Admin(brow);

	@Test
	public void AddPhoto() throws Exception {
		String testCaseName = this.toString();
		System.out.println(testCaseName);
		brow.startBrowserFirefox("firefox");
		brow.maximiseBrowser();
		// verify title
		brow.verifyTitle("OrangeHRM - New Level of HR Management");
		login.setUp();
		login.loginToapp("qaplanet1", "user1");
		brow.waitForPageToLoad();
		// verify title
		brow.verifyTitle("OrangeHRM");
		brow.getscreenshot();

		// Mouse over on PIM
		brow.MouseOverToElement("id", "pim");

		Thread.sleep(3000);

		// Click on Add employee
		brow.click("xpath", "//li[@id='pim']/ul/li[2]/a");
		// li[@id='admin']/ul/li/a

		// Switch to the frame
		brow.selectFrameById("rightMenu");

		Thread.sleep(4000);

		// Verify PIM : Add Employee text
		brow.verifyText("xpath", "//*[@id='frmEmp']/div/div[1]/div[2]/div[1]/h2", "PIM : Add Employee");

		// Enter LastName
		brow.sendKeys("id", "txtEmpLastName", "Kumar");

		Thread.sleep(1000);

		// Enter First Name
		brow.sendKeys("id", "txtEmpFirstName", "Naveen");

		// Click on Browse
		brow.click("id", "photofile");
		
		Thread.sleep(3000);

		// Upload photo
		Runtime.getRuntime().exec("C:\\Users\\Naveen\\Desktop\\AutoIT\\FileUpload.exe");
	}
}




