package LamdaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class DragDropSlidersTest  {

	public WebDriver driver;

	private String username = "kumar95530";
	private String accessKey = "nS7iTuxqWP9CdAPiKWtfJNrLAbCgip6Q7qnDg1LfkO1sNrE2nB";
	private String hub = "@hub.lambdatest.com/wd/hub";


	 By dragDrop = By.linkText("Drag & Drop Sliders");
	 By Defaultvalue = By.xpath("//input[@value='15']");




	@Parameters(value={"browser", "version", "platform"})
	@BeforeMethod	
	public void setUp(String browser, String version, String platform) {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.addArguments("--remote-allow-origins=*");
		browserOptions.setPlatformName(platform);
		browserOptions.setBrowserVersion(version);
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
	public void Drag_Drop() {

		driver.get("https://www.lambdatest.com/selenium-playground");

		driver.manage().window().maximize();

		getElement(dragDrop);

		doClick(dragDrop);

		waitForElementVisible(Defaultvalue, 5);
		ActionsDragAndDrop((WebElement) Defaultvalue, 118, 15);



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

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public void ActionsDragAndDrop(WebElement value ,int xOffset , int yOffset) {
		Actions act = new Actions(driver);
		act.dragAndDropBy(value, yOffset, xOffset).perform();
	}


	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}



