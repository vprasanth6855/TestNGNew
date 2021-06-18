package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterTest {
	
	WebDriver driver;

	By registerLink = By.linkText("Register");
	By registerAccountText = By.xpath("//h1[text()='Register Account']");
	By personalDetails = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/form[1]/fieldset[1]/legend[1]");
	
	@BeforeTest
	@Parameters({"url", "browser"})
	public void setUp(String url, String browserName) throws Exception{
		if(browserName.equals("chrome")){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else{
			System.out.println("please pass the correct browsername..");
			throw new Exception("NoSuchBrwoserException");
		}
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountTest(){
		//driver.findElement(registerLink).click();
		Assert.assertTrue(driver.findElement(registerAccountText).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifyPersonalDetails(){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(personalDetails));
		
		Assert.assertEquals(driver.findElement(personalDetails).getText(), "Your Personal Details");
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
			}
