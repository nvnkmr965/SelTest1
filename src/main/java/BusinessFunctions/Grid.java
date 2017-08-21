package BusinessFunctions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import LIB.Common;

public class Grid {
	Common browser;
	// constructor with one argument.
		public Grid(Common br) {
			browser = br;
		}

	public static RemoteWebDriver getDriver(String browser) throws MalformedURLException {
		return new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"), getBrowserCapabilities(browser));
	}

	public static DesiredCapabilities getBrowserCapabilities(String browserType) {
		switch (browserType) {
		case "firefox":
			System.out.println("Opening firefox driver");
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Naveen\\Desktop\\eclipse\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			return DesiredCapabilities.firefox();
		case "chrome":
			System.out.println("Opening chrome driver");
			return DesiredCapabilities.chrome();
		case "IE":
			System.out.println("Opening IE driver");
			return DesiredCapabilities.internetExplorer();
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			return DesiredCapabilities.firefox();
		}
	}

	public static RemoteWebDriver driver;
	public static String appURL = "http://www.google.com";

	public void setUp(String browser) throws MalformedURLException {
		System.out.println("*******************");
		driver = getDriver(browser);
		driver.manage().window().maximize();
	}

	public void testGooglePageTitleInFirefox() {
		driver.navigate().to(appURL);
		String strPageTitle = driver.getTitle();
		//Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
	}

}