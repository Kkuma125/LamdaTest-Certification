package LamdaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




public class DragDropSlidersTest  {

	public RemoteWebDriver driver = null;

						//Test Scenario 2:


	private String username = "kumar95530";
	private  String accessKey = "vJU4bQvzg6oI6XQrGW9BLG6PyeRi9nnP9RvW5z7SizVLleFMHF";
	private  String hub = "@hub.lambdatest.com/wd/hub";

	By dragDrop = By.linkText("Drag & Drop Sliders");
	By Defaultvalue = By.xpath("//input[@value='15']");




	@Parameters({"browserName", "browserVersion", "osVersion"})
	@BeforeMethod
	public void setUp2(String browser, String browserVersion, String osVersion) {

		FirefoxOptions browserOptions = new FirefoxOptions();
		browserOptions.setCapability("browserName", browser);
		browserOptions.setPlatformName(osVersion);
		browserOptions.setBrowserVersion(browserVersion);
		browserOptions.setAcceptInsecureCerts(true);
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "Drag and Drop");
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
	public void Drag_Drop() {

		getElement(dragDrop);

		doClick(dragDrop);

		waitForElementVisible(Defaultvalue, 5);
		ActionsDragAndDrop(Defaultvalue, 118, 115);



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

	private void ActionsDragAndDrop(By defaultvalue2, int xOffset, int yOffset) {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(Defaultvalue);
		act.dragAndDropBy(ele, yOffset, xOffset).perform();;

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}
}




