package LamdaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UserInputForm {


	public RemoteWebDriver driver = null;

	           //Test Scenario 3:


	private String username = "kumar95530";
	private  String accessKey = "vJU4bQvzg6oI6XQrGW9BLG6PyeRi9nnP9RvW5z7SizVLleFMHF";
	private  String hub = "@hub.lambdatest.com/wd/hub";

	By clickinput = By.xpath("//a[normalize-space()='Input Form Submit']");
	By name = By.xpath("//input[@id='name']");
	By emailid = By.xpath("//input[@id='inputEmail4']");
	By password = By.xpath("//input[@id='inputPassword4']");
	By company = By.xpath("//input[@id='company']");
	By website = By.xpath("//input[@id='websitename']");
	By country = By.xpath("//select[@name='country']");
	By city = By.xpath("//input[@id='inputCity']");
	By address1 = By.xpath("//input[@id='inputAddress1']");
	By address2 = By.xpath("//input[@id='inputAddress2']");
	By state = By.xpath("//input[@id='inputState']");
	By zipcode = By.xpath("//input[@id='inputZip']");
	By button = By.xpath("//button[text()='Submit']");
	By SuccessMessage = By.xpath("//p[@class='success-msg hidden']");
	By windowpopup = By.id("exit_popup_close");


	@Parameters({"browserName", "browserVersion", "osVersion"})
	@BeforeMethod
	public void setUp3(String browser, String browserVersion, String osVersion) {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setCapability("browserName", browser);
		browserOptions.setPlatformName(osVersion);
		browserOptions.setBrowserVersion(browserVersion);
		browserOptions.setAcceptInsecureCerts(true);	
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "User Inpiut Form");
		ltOptions.put("console", "true");


		browserOptions.setCapability("LT:Options", ltOptions);



		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" +
					accessKey + hub), browserOptions);
		} catch(MalformedURLException exc) {
			exc.printStackTrace();
		}


		driver.get("https://www.lambdatest.com/selenium-playground");
		driver.manage().window().maximize();


	}
	@Test
	public void userInputvalidation() {

		getElement(clickinput);
		doClick(clickinput);
		doSendKeys(name, "Vinod");
		doSendKeys(emailid, "vinod@gmail.com");
		doSendKeys(password, "12345");
		doSendKeys(company, "abc");
		doSendKeys(website, "xyz.com");
		waitForElementVisible(windowpopup, 10);
		doClick(windowpopup);
		doDropDownSelectByVisibleText(country, "United States");
		doSendKeys( city, "New York");
		doSendKeys(address1, "New York, NY 10011");
		doSendKeys(address2, "13th Street");
		doSendKeys(state, "New York");
		doSendKeys(zipcode, "10001");
		
		waitForElementVisibleclickable(button, 10);

		getElement(button);
		doClick(button);
		
	
		
		
		String Message = getElement(SuccessMessage).getText();
		System.out.println(Message);
		Assert.assertEquals(Message, "Thanks for contacting us, we will get back to you shortly."); //user validation success

	}

	private WebElement getElement(By locator) {		 
		return driver.findElement(locator);
	}

	private void doClick(By locator) {
		getElement(locator).click();
	}

	private void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	private WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	private List<WebElement> waitForElementVisibleUrl(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	private void doDropDownSelectByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	private WebElement waitForElementVisibleclickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}
}


