package TestNGSessions;

import org.testng.annotations.Test;

public class TestNG_04_ExpectedExceptionConcept {

	@Test(expectedExceptions = Exception.class)
	public void loginTest(){
		System.out.println("login test...starting...");
		int i =9/0;
		System.out.println("login test...ending...");
	}
}
