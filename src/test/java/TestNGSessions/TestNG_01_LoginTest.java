package TestNGSessions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_01_LoginTest {

	WebDriver driver;

	By username = By.id("input-email");
	By password = By.id("input-password");
	By login = By.xpath("//input[@value='Login' and @type='submit']");
	By myAccount = By.linkText("My Account");
	By yourStore = By.linkText("Your Store");
			
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test(priority=1)
	public void myAccount(){
		Assert.assertTrue(driver.findElement(myAccount).isDisplayed());
	}
	
	@Test(priority=2)
	public void pageTitleTest(){
		String title = driver.getTitle();
		System.out.println("page title is: "+title);
		Assert.assertEquals(title, "Account Login");
	}
	
	@Test(priority=3)
	public void loginTest(){
		driver.findElement(username).sendKeys("veenasmohan04@gmail.com");
		driver.findElement(password).sendKeys("MadhavNeerav@6855");
		driver.findElement(login).click();
		
		String store = driver.findElement(yourStore).getText();
		Assert.assertEquals(store, "Your Store");
		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
