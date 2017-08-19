package TestScripts;

import org.testng.annotations.Test;

import BusinessFunctions.Admin;
import BusinessFunctions.Login;

public class ScrollTo extends RootTest{
	static Login login = new Login(brow);
	static Admin admin = new Admin(brow);
	
	@Test
	public void Scroll() throws Exception {

	/*	String testCaseName = this.toString();
		System.out.println("TestcaseName is " + testCaseName);*/
		brow.startBrowserFirefox("Firefox");
		brow.maximiseBrowser();
		// verify title
		brow.verifyTitle("OrangeHRM - New Level of HR Management");
		login.setUp();
		login.loginToapp("qaplanet1", "user1");
		brow.waitForPageToLoad();

		// verify title
		brow.verifyTitle("OrangeHRM");

		// Add location2
		admin.CompanyScructure();
		
		Thread.sleep(3000);
		
		brow.scroll();	
		
		// brow.QuitObject();
	}

}
