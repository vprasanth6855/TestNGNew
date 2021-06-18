package TestNGSessions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_08_SoftAssert {
	WebDriver driver;
	SoftAssert softAssert;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		softAssert = new SoftAssert();

	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("Account Login"));
		
		String title = driver.getTitle();
		System.out.println("Page title is: "+title);
		Assert.assertEquals(title, "Account Login", "Login title is mismatched..");
	}
	
	@Test(priority=2)
	public void myAccountLinkTest(){
		boolean flag = driver.findElement(By.linkText("My Account")).isDisplayed();
		Assert.assertTrue(flag, "link is not available...");
	}
	
	@Test(priority=3)
	public void loginTest(){
		driver.findElement(By.id("input-email")).sendKeys("veenasmohan04@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("MadhavNeerav@6855");
		driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]")).click();
		softAssert.assertTrue(driver.findElement(By.linkText("Your Store")).isDisplayed(), "Link is not displayed...");
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Your Store")));
		
		String Header = driver.findElement(By.linkText("Your Store")).getText();
		System.out.println("Header is: "+Header);
		softAssert.assertEquals(Header, "Your Store12", "Header text is not matched...");
		
		String title = driver.getTitle();
		System.out.println("title is: "+title);
		softAssert.assertEquals(title, "My Account12");
		
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
