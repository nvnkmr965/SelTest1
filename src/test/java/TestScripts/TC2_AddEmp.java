package TestScripts;
import org.testng.annotations.Test;

import BusinessFunctions.Admin;
import BusinessFunctions.Login;
public class TC2_AddEmp extends RootTest
{
	static Login login=new Login(brow);
	static Admin admin=new Admin(brow);

	@Test
	public static void AddEmployee() throws Exception
	{
		brow.startBrowserFirefox("Firefox");
		brow.maximiseBrowser();
		//verify title
		brow.verifyTitle("OrangeHRM - New Level of HR Management");
		login.setUp();
		login.loginToapp("qaplanet1","user1");
		brow.waitForPageToLoad();
		//verify title
		brow.verifyTitle("OrangeHRM");
		
		login.addEmp("QA","Planet");
		login.verifyItemInEmpList("QA","Planet");
		login.logout();
		brow.closeBrowser();
		//brow.QuitObject();
	}
}
