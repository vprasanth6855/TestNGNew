package TestNGSessions;

import org.testng.annotations.Test;

public class TestNG_05_InvocationCount {
	
	@Test(invocationCount=10)
	public void createUser(){
		System.out.println("create user test...");
	}
}
