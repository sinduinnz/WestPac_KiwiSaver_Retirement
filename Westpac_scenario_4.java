package Demo_pkg;

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

public class Westpac_scenario_4 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
       System.setProperty("webdriver.chrome.driver", "C:\\chrome_driver\\chromedriver.exe");
		
       WebDriver driver = new ChromeDriver();
		
	   driver.get("https://www.westpac.co.nz/");

	   //Explicit Wait.
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   	   
	   //Instantiate Actions Class.
	   Actions actions = new Actions(driver);
	   
	   //Retrieve kiwisaver WebElement
	   WebElement kiwiSaver = driver.findElement(By.xpath("//a[@id='ubermenu-section-link-kiwisaver-ps']"));
	   
	   //Explicit Wait on kiwisaver WebElement. 
	   wait.until(ExpectedConditions.elementToBeClickable(kiwiSaver));
	   
	   //Mousehover on Kiwisaver webelement on WestPac Main Page. 
	   actions.moveToElement(kiwiSaver).perform();
	   System.out.println("Mousehover done on Kiwisaver on WestPac Main Page");
	   
	   //Retrieve Kiwisaver Calculator WebElement
	   WebElement kiwiSaverCalculator = driver.findElement(By.xpath("//a[contains(text(),'KiwiSaver calculators')]"));
	   
	   //Explicit Wait on kiwisaver Calculator WebElement. 
	   wait.until(ExpectedConditions.elementToBeClickable(kiwiSaverCalculator));
	   
	   //Clicking on KiwiSaver Calculator
	   kiwiSaverCalculator.click();
	   
	   Thread.sleep(8000); 

	   
	   //Retrieve "Click Here to Get Started " Webelement present under "WestPac KiwiSaver Scheme Retirement Calculator"
	   WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click here to get started.']"));	   
	   
	   //Explicit Wait on clickHere WebElement. 
	   wait.until(ExpectedConditions.elementToBeClickable(clickHere));
	   
	   //Clicking on "Click Here to Get Started " Webelement
	   clickHere.click();
	   System.out.println("Click Here to Get Started under WestPac KiwiSaver Scheme Retirement Calculator clicked");
	   
	   Thread.sleep(8000); 
	   
	    try
	    {
	       driver.switchTo().frame(0);
	       int current_Age = 55;
	       int current_KiwiSaverBalance = 140000;
	       
	       //Current Age
	       List<WebElement> list1 = driver.findElements(By.xpath(".//*[@type='text']"));
	       for(WebElement el1 : list1) 
	    	{
	    	     wait.until(ExpectedConditions.elementToBeClickable(el1));
	    	     el1.sendKeys(""+current_Age);
         	     System.out.println("Current age field value entered as " +current_Age);
         	     break;
         	     
           	}
	       
	       //Select dropDown.
	       driver.findElement(By.cssSelector("div.well-value.ng-binding")).click();
	       
	       //Not-Employed
	       driver.findElement(By.cssSelector("ul>li:nth-child(4)")).click();;
	       
	       //Current KiwiSaver Balance.
	       driver.findElement(By.xpath("//*[@model='ctrl.data.KiwiSaverBalance']/div[1]/div[1]/div[1]/input")).sendKeys(""+current_KiwiSaverBalance);
	       
	       //Voluntary Contributions
	       driver.findElement(By.xpath("//*[@model='ctrl.data.VoluntaryContributions']/div[1]/div[1]/div[1]/input")).sendKeys(""+10);
	       
	       //Click on Frequency dropDown
	       driver.findElement(By.xpath("//div[@ng-if='hasPeriod']/div[1]/div[1]/div[1]")).click();
	       
	       //Select Anually
	       driver.findElement(By.xpath("//li[@value='year']/div")).click();
	       
	       //Risk Profile-- Balanced.
	       driver.findElement(By.xpath("//input[@id='radio-option-019']")).click();
	       
	       //Savings Goal at retirement.
	       driver.findElement(By.xpath("//*[@model='ctrl.data.SavingsGoal']/div[1]/div[1]/div[1]/input")).sendKeys(""+200000);
	       
	       //view your kiwisaver retirement projection.
	       driver.findElement(By.cssSelector("span.label[ng-show='ctrl.data.formCompleted']")).click();
	       
	       //User should be able to see the kiwisaver balnce at age 65.
	       
	       String expected_Kiwisaver_Result_Title = "At age 65, your KiwiSaver balance is estimated to be:";
	       
	       String actual_Kiwisaver_Result_Title = driver.findElement(By.xpath("//span[text()='At age 65, your KiwiSaver balance is estimated to be:']")).getText();
	       
	       Assert.assertEquals(actual_Kiwisaver_Result_Title, expected_Kiwisaver_Result_Title);
	       	       
	       String expected_Retirement_Amount = "$197,679";
	       String actual_Retirement_Amount = driver.findElement(By.cssSelector("span.result-value.result-currency.ng-binding")).getText();
	       
	       actual_Retirement_Amount = actual_Retirement_Amount.replaceAll("\\s{1,}", "");
	       
	       Assert.assertEquals(expected_Retirement_Amount, actual_Retirement_Amount);
       
	      }
	    
  
	      catch(Exception e)
	      {
	    	System.out.println(e.getMessage());  
	      }
	      
		}

}
