package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestNew {

	WebDriver driver;

	By username = By.id("input-email");
	By password = By.id("input-password");
	By login = By.xpath("//input[@value='Login' and @type='submit']");
	By myAccount = By.linkText("My Account");
	By yourStore = By.linkText("Your Store");

	@BeforeTest
	@Parameters({ "url", "browser" })
	public void setUp(String url, String browserName) throws Exception {
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else{
			System.out.println("plesae pass the correct browser name...");
			throw new Exception("NoSuchBrowserException");
		}
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void myAccount() {
		Assert.assertTrue(driver.findElement(myAccount).isDisplayed());
	}

	@Test(priority = 2)
	public void pageTitleTest() {
		String title = driver.getTitle();
		System.out.println("page title is: " + title);
		Assert.assertEquals(title, "Account Login");
	}

	@Test(priority = 3)
	@Parameters({ "username", "password" })
	public void loginTest(String emailID, String pwd) {
		driver.findElement(username).sendKeys(emailID);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(login).click();

		String store = driver.findElement(yourStore).getText();
		Assert.assertEquals(store, "Your Store");

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
