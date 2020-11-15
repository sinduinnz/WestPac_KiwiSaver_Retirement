//******* USER STORY 1 *****************************************************************************
//**1st scenario--- Clicking on information icon next to Current Age and verifying the message displayed******
package Demo_pkg;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;

public class Westpac_scenario_1 {

	WebDriver driver = null;

	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chrome_driver\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("USER STORY 1");
		System.out.println(
				"Running 1st scenario--- Clicking on information icon next to Current Age and verifying the message displayed");
		System.out.println("Opening WestPac homepage");
		driver.get("https://www.westpac.co.nz/");

	}

	@Test
	public void Test1() throws InterruptedException {

		// Explicit Wait.+
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Instantiate Actions Class.
		Actions actions = new Actions(driver);

		// Explicit Wait on kiwisaver WebElement.
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='ubermenu-section-link-kiwisaver-ps']")));

		// Retrieve kiwisaver WebElement
		WebElement kiwiSaver = driver.findElement(By.xpath("//a[@id='ubermenu-section-link-kiwisaver-ps']"));

		// Mousehover on Kiwisaver webelement on WestPac Main Page.
		actions.moveToElement(kiwiSaver).perform();
		System.out.println("Mousehover done on Kiwisaver on WestPac Main Page");

		// Explicit Wait on kiwisaver Calculator WebElement.
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'KiwiSaver calculators')]")));

		// Retrieve Kiwisaver Calculator WebElement
		WebElement kiwiSaverCalculator = driver.findElement(By.xpath("//a[contains(text(),'KiwiSaver calculators')]"));

		// Clicking on KiwiSaver Calculator
		kiwiSaverCalculator.click();

		Thread.sleep(8000);

		// Explicit Wait on clickHere WebElement.
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Click here to get started.']")));

		// Retrieve "Click Here to Get Started " Webelement present under "WestPac
		// KiwiSaver Scheme Retirement Calculator"
		WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click here to get started.']"));

		// Clicking on "Click Here to Get Started " Webelement
		clickHere.click();
		System.out.println("Click Here to Get Started under WestPac KiwiSaver Scheme Retirement Calculator clicked");

		Thread.sleep(8000);

		String expected_CurrentAge_Msg = "This calculator has an age limit of 18 to 64 years old.";

		try {
			driver.switchTo().frame(0);
			List<WebElement> list = driver.findElements(By.xpath(".//*[@class='icon']"));
			for (WebElement el : list) {
				wait.until(ExpectedConditions.elementToBeClickable(el));
				el.click();
				System.out.println("Information icon next to current age clicked");

				String actual_CurrentAge_Msg = driver.findElements(By.xpath(".//p")).get(0).getText();

				if (expected_CurrentAge_Msg.equalsIgnoreCase(actual_CurrentAge_Msg)) {
					System.out.println("The expected Message is same as actual Message --- " + actual_CurrentAge_Msg);

				} else {
					System.out
							.println("The expected Message is not same as actual Message --- " + actual_CurrentAge_Msg);

				}
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterClass
	public void teardown() throws Exception {
		System.out.println("Closing the Driver");
		driver.quit();
	}
}
