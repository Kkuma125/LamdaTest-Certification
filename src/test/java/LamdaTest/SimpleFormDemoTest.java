package LamdaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class SimpleFormDemoTest {

	public WebDriver driver;


	private String username = "kumar95530";
	private String accessKey = "nS7iTuxqWP9CdAPiKWtfJNrLAbCgip6Q7qnDg1LfkO1sNrE2nB";
	private String hub = "@hub.lambdatest.com/wd/hub";


	By SimpleForm  = By.xpath("//a[normalize-space()='Simple Form Demo']");
	By EnterMessage = By.xpath("//input[@id='user-message']");
	By CLickChecked = By.xpath("//button[@id='showInput']");
	By YourMessage = By.cssSelector("div#user-message #message");





	@Parameters(value={"browser", "version", "platform"})
	@BeforeMethod	
	public void setUp(String browser, String version, String platform) {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.addArguments("--remote-allow-origins=*");
		browserOptions.getBrowserName();
		browserOptions.setBrowserVersion(version);
		browserOptions.setPlatformName(platform);
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("console", "true");
		ltOptions.put("selenium_version", "4.8.0");
		browserOptions.setCapability("LT:Options", ltOptions);

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" +
					accessKey + hub), browserOptions);
		} catch(MalformedURLException exc) {
			exc.printStackTrace();
		}




	}



	@Test
	public void SimpleForm () {

		driver.get("https://www.lambdatest.com/selenium-playground");
		driver.manage().window().maximize();
		String url = driver.getCurrentUrl();
		System.out.println(url);

		if(url.contains("simple-form-demo")){
			System.out.println("Pass");
		}
		else {
			System.out.println("Fail");
			Assert.assertEquals(url, "simple-form-demo");// Verifying URl Contains simple-form-demo

			getElement(SimpleForm);

			doClick(SimpleForm);

			String value = "Welcome to LambdaTest";
			doSendKeys(EnterMessage, value);
			getElement(CLickChecked);
			doClick(CLickChecked); 


			String YourMes = getElement(YourMessage).getText();
			System.out.println(YourMes);
			Assert.assertEquals(YourMes, "Welcome to LambdaTest");  //Verifying YourMessage Contains Welcome to LambdaTest
		}


	}	



	public WebElement getElement(By locator) {		
		return driver.findElement(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}





	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
