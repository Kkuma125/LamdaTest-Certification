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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class SimpleFormDemoTest {

	public RemoteWebDriver driver = null;

	             //Test Scenario 1:


	private String username = "kumar95530";
	private  String accessKey = "vJU4bQvzg6oI6XQrGW9BLG6PyeRi9nnP9RvW5z7SizVLleFMHF";
	private  String hub = "@hub.lambdatest.com/wd/hub";



	By SimpleForm  = By.xpath("//a[normalize-space()='Simple Form Demo']");
	By EnterMessage = By.xpath("//input[@id='user-message']");
	By CLickChecked = By.xpath("//button[@id='showInput']"); 
	By YourMessage = By.cssSelector("div#user-message #message");



	@Parameters({"browserName", "browserVersion", "osVersion"})
	@BeforeMethod
	public void setUp1(String browser, String browserVersion, String osVersion) {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setCapability("browserName", browser);
		browserOptions.setPlatformName(osVersion);
		browserOptions.setBrowserVersion(browserVersion);
		browserOptions.setAcceptInsecureCerts(true);	
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "SimpleFormDemo");
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
	public void SimpleForm () {

		waitForElementVisible(SimpleForm, 10);

		getElement(SimpleForm);

		doClick(SimpleForm);

		String value = "Welcome to LambdaTest";
		doSendKeys(EnterMessage, value);
		getElement(CLickChecked);
		doClick(CLickChecked); 


		String YourMes = getElement(YourMessage).getText();
		System.out.println(YourMes);
		Assert.assertEquals(YourMes, "Welcome to LambdaTest");  //Verifying YourMessage Contains Welcome to LambdaTest



		String url = driver.getCurrentUrl();
		System.out.println(url);

		if(url.contains("https://www.lambdatest.com/selenium-playground")){
			System.out.println("Pass");
		}

		else {
			System.out.println("Fail");
			Assert.assertEquals(url, "simple-form-demo");// Verifying URl Contains simple-form-demo
		}

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


	@AfterMethod
	public void tearDown() {

		driver.quit();
	}
}