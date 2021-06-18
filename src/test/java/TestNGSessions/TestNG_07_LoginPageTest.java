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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_07_LoginPageTest {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("Account Login"));
		
		String title = driver.getTitle();
		System.out.println("Page title is: "+title);
		Assert.assertEquals(title, "Account Login", "Login title is mismatched..");
		
//		if(title.equals("Rediffmail123")){
//			System.out.println("PASS");
//		}
//		else{
//			System.out.println("FAIL");
//		}
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
		Assert.assertTrue(driver.findElement(By.linkText("Your Store")).isDisplayed(), "Link is not displayed...");
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Your Store")));
		
		String Header = driver.findElement(By.linkText("Your Store")).getText();
		System.out.println("Header is: "+Header);
		Assert.assertEquals(Header, "Your Store12", "Header text is not matched...");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
