package LIB;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
	WebDriver driver;
	Actions action;

	// Start firefox Browser
	public void startBrowserFirefox(String browser) {

		switch (browser) {
		case "Firefox": {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Naveen\\Desktop\\eclipse\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.navigate().to("http://apps.qaplanet.in/qahrm/login.php");
			break;
		}
		case "IE": {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\Naveen\\Documents\\eclipse\\IEDriverServer\\IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

			caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://apps.qaplanet.in/qahrm/login.php");

			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			driver = new InternetExplorerDriver(caps);
			driver.navigate().to("http://apps.qaplanet.in/qahrm/login.php");
			break;

		}
		case "chrome": {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Naveen\\Documents\\eclipse\\ChromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.navigate().to("http://apps.qaplanet.in/qahrm/login.php");
			break;
		}
		}

	}

	// Start scroll
	public void scroll() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//jse.executeScript("window.scrollUp(0,900)", "");
		/*jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
				+ "document.body.scrollHeight,document.documentElement.clientHeight));");*/
		
		for (int second = 0;; second++) {
	        if(second >=60){
	            break;
	        }
	            ((JavascriptExecutor) driver).executeScript("window.scroll(0,400)", ""); //y value '400' can be altered
	            Thread.sleep(3000);
	}
	}

	// Maximize Browser
	public void maximiseBrowser() {
		driver.manage().window().maximize();
	}

	public void getscreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils.copyFile(scrFile,
				new File("C:\\Users\\Naveen\\Downloads\\HRM_FinalFramework\\test-output\\Screenshots\\screenshot.png"));
	}

	// WebElement
	public WebElement webElementId(String identifier, String locator) {
		WebElement e = null;
		switch (identifier) {
		case "id":
			e = driver.findElement(By.id(locator));
			break;
		case "className":
			e = driver.findElement(By.className(locator));
			break;
		case "tagName":
			e = driver.findElement(By.tagName(locator));
			break;
		case "name":
			e = driver.findElement(By.name(locator));
			break;
		case "linkText":
			e = driver.findElement(By.linkText(locator));
			break;
		case "partialLinkText":
			e = driver.findElement(By.partialLinkText(locator));
			break;
		case "cssSelector":
			e = driver.findElement(By.cssSelector(locator));
			break;
		case "xpath":
			e = driver.findElement(By.xpath(locator));
			break;
		default:
			System.out.println("Locator not found");
			e = null;
		}
		return e;
	}

	// Sendkey general method
	public void sendKeys(String identifier, String locator, String content) {
		WebElement e = webElementId(identifier, locator);
		e.sendKeys(content);
	}

	// Clear text field method
	public void ClearTextField(String identifier, String locator) {
		WebElement e = webElementId(identifier, locator);
		e.clear();
	}

	// click general method
	public void click(String identifier, String locator) {
		WebElement e = webElementId(identifier, locator);
		e.click();
	}

	// verify title of the page
	public void verifyTitle(String title) {
		if (driver.getTitle().equals(title)) {
			System.out.println(title + " displayed");
		} else {
			System.out.println("Failed to display " + title);
			return;
		}
	}

	// Wait until the Element is present
	public void waitUntilElementPresent(String elementpath) {
		WebElement elementpresent = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementpath)));
	}

	// Verify Text
	public void verifyText(String identifier, String locator, String text) {
		WebElement e = webElementId(identifier, locator);
		if (e.getText().equals(text)) {
			System.out.println(text + " displayed");
		} else {
			System.out.println(text + "Did not displayed");
		}
	}

	// Verify Text
	public boolean verifyPopup(String popupMsg) {
		WebDriverWait wait = new WebDriverWait(driver, 15000);
		Alert popup = wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(popup.getText());
		if (popup.getText().equals(
				"Please correct the following\n\n	- Location Name has to be specified\n	- Country should be selected!\n	- Address should be specified\n	- Zip Code should be specified\n")) {
			popup.accept();
			return true;
		} else {
			return false;
		}
	}

	// verify element present
	public void verifyElementPresent(String identifier, String locator) throws InterruptedException {
		Thread.sleep(3000);
		WebElement e = webElementId(identifier, locator);
		if (e.isDisplayed()) {
			System.out.println("Element present");
		} else {
			System.out.println("Element is not present");
		}
	}

	// Thread sleep
	public void sleepThread(long sleeptime) {
		try {
			Thread.sleep(sleeptime);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Wait for page to load
	public void waitForPageToLoad() {
		try {
			for (int i = 0; i < 50;) {
				if (driver.getTitle().length() != 0) {
					System.out.println("Page loaded");
					i = 51;
					break;
				} else {
					Thread.sleep(1000);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// select frame by id
	public void selectFrameById(String locator) {
		driver.switchTo().frame(locator);
	}

	// Select frame default method
	public void selectFrameDefault() {
		driver.switchTo().defaultContent();
	}

	// Select frame default method
	public void selectCountry() {
		WebElement CountryCombo = driver.findElement(By.id("cmbCountry"));

		// create select object
		Select objSel = new Select(CountryCombo);
		List<WebElement> ListOfCountries = objSel.getOptions();

		// We can use the findElements also to get the all the items instead of
		// Select
		// List<WebElement> ListOfCountries =
		// driver.findElements(By.id("cmbCountry"));

		for (WebElement option : ListOfCountries) {
			String CountryName = option.getText();
			if (CountryName.equals("India")) {
				System.out.println(CountryName);
				objSel.selectByVisibleText("India");
				break;
			}

			/*
			 * // We can use Iterator inplace of foreach loop
			 * Iterator<WebElement> items = ListOfCountries.iterator(); while
			 * (items.hasNext()) { WebElement EIT=items.next();
			 * System.out.println(EIT.getText());
			 * System.out.println(items.next().getText()); }
			 */
		}
	}

	// getting data from table and verifying it with the required text
	public boolean verifyElementInTable(String xpathlocator, String text) {
		boolean a = false;
		List<WebElement> tdlist = driver.findElements(By.xpath(xpathlocator));
		for (WebElement el : tdlist) {
			if (el.getText().equals(text)) {
				a = true;
				break;
			}
			System.out.println(el.getText());
		}

		if (a == true) {
			System.out.println(text + " was identifed");
		} else {
			System.out.println(text + " was not identifed");
		}
		return a;
	}

	// getting data from table and verifying it with the required text
	public boolean verifyLocationInTable(String xpathlocator, String text) {
		boolean a = false;
		List<WebElement> tdlist = driver.findElements(By.xpath(xpathlocator));
		for (WebElement el : tdlist) {
			System.out.println(el.getText());
			if (el.getText().equals(text)) {
				a = true;
				break;
			}
		}

		if (a == true) {
			System.out.println(text + " was identifed");
		} else {
			System.out.println(text + " was not identifed");
		}
		return a;
	}

	// Start action
	public void startAction() {
		action = new Actions(driver);
	}

	// move to element
	public void MouseOverToElement(String identifier, String locator) {
		action = new Actions(driver);
		WebElement e = webElementId(identifier, locator);
		action.moveToElement(e).perform();
	}

	// close
	public void closeBrowser() {
		driver.close();
	}

	// Quit
	public void QuitObject() {
		driver.quit();
	}
}