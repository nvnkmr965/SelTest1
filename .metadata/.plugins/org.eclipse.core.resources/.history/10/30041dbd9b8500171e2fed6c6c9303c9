package TestScripts;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BusinessFunctions.CustomReport;
import BusinessFunctions.Login;

//Add listener to listen report and write it when test case finished
@Listeners(value=CustomReport.class)

public class Tc1_Login extends RootTest 
{
	static Login login=new Login(brow);
	@Test
	public void verifyOrangeHRM() throws Exception
	{				
		String testCaseName = this.toString();
		brow.startBrowserFirefox("chrome");
		brow.maximiseBrowser();
		//verify title
		brow.verifyTitle("OrangeHRM - New Level of HR Management");
		login.setUp();
		login.loginToapp("qaplanet1","user1");
		brow.waitForPageToLoad();
		//verify title
		brow.verifyTitle("OrangeHRM");
		brow.getscreenshot();
		login.logout();
		BusinessFunctions.DataInExcel.setPassOrFail("Sheet1", "Pass");
		brow.closeBrowser();
		 //Pass test case

        Assert.assertTrue(true);
		//brow.QuitObject();
	}
}
