//******* USER STORY 2 2nd SCENARIO *****************************************************************************
//***User whose current aged 45 is Self-employed, current KiwiSaver balance is $100000, voluntary contributes $90 fortnightly and chooses Conservative risk profile with saving goals requirement of $290000 can calculate his projected balances at retirement.

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
import org.testng.Assert;
import org.testng.annotations.*;

public class Westpac_Scenario_3 {

	WebDriver driver = null;

	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chrome_driver\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println(
				"Running User story 2 -- 2nd scenario--- User whose current aged 45 is Self-employed, current KiwiSaver balance is $100000, voluntary contributes $90 fortnightly and chooses Conservative risk profile with saving goals requirement of $290000 can calculate his projected balances at retirement.\r\n");
		System.out.println("Opening WestPac homepage");
		driver.get("https://www.westpac.co.nz/");

	}

	@Test
	public void Test1() throws InterruptedException {

		// Explicit Wait.
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

		try {
			driver.switchTo().frame(0);
			int current_Age = 45;
			int current_KiwiSaverBalance = 100000;

			// Current Age
			List<WebElement> list1 = driver.findElements(By.xpath(".//*[@type='text']"));
			for (WebElement el1 : list1) {
				wait.until(ExpectedConditions.elementToBeClickable(el1));
				el1.sendKeys("" + current_Age);
				System.out.println("Current age field value entered as " + current_Age);
				break;

			}

			// Select dropDown.
			driver.findElement(By.cssSelector("div.well-value.ng-binding")).click();

			// Self-Employed
			driver.findElement(By.cssSelector("ul>li:nth-child(3)")).click();
			;

			// Current KiwiSaver Balance.
			driver.findElement(By.xpath("//*[@model='ctrl.data.KiwiSaverBalance']/div[1]/div[1]/div[1]/input"))
					.sendKeys("" + current_KiwiSaverBalance);

			// Voluntary Contributions
			driver.findElement(By.xpath("//*[@model='ctrl.data.VoluntaryContributions']/div[1]/div[1]/div[1]/input"))
					.sendKeys("" + 90);

			// Click on Frequency dropDown
			driver.findElement(By.xpath("//div[@ng-if='hasPeriod']/div[1]/div[1]/div[1]")).click();

			// Select fortnight
			driver.findElement(By.xpath("//li[@value='fortnight']/div")).click();

			// Risk Profile-- Conservative.
			driver.findElement(By.xpath("//input[@id='radio-option-016']")).click();

			// Savings Goal at retirement.
			driver.findElement(By.xpath("//*[@model='ctrl.data.SavingsGoal']/div[1]/div[1]/div[1]/input"))
					.sendKeys("" + 290000);

			// view your kiwisaver retirement projection.
			driver.findElement(By.cssSelector("span.label[ng-show='ctrl.data.formCompleted']")).click();

			// User should be able to see the kiwisaver balnce at age 65.
			String expected_Kiwisaver_Result_Title = "At age 65, your KiwiSaver balance is estimated to be:";
			String actual_Kiwisaver_Result_Title = driver
					.findElement(By.xpath("//span[text()='At age 65, your KiwiSaver balance is estimated to be:']"))
					.getText();

			Assert.assertEquals(actual_Kiwisaver_Result_Title, expected_Kiwisaver_Result_Title);

			String expected_Retirement_Amount = "$259,581";
			String actual_Retirement_Amount = driver
					.findElement(By.cssSelector("span.result-value.result-currency.ng-binding")).getText();

			actual_Retirement_Amount = actual_Retirement_Amount.replaceAll("\\s{1,}", "");

			Assert.assertEquals(expected_Retirement_Amount, actual_Retirement_Amount);

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterClass
	public void teardown() throws Exception {
		System.out.println("Closing the Driver");
		driver.quit();
	}

}
