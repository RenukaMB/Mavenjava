package qaframework.webElements;

import static org.testng.Assert.*;

import org.openqa.selenium.By;

public class Assertions {

	public By webDriverBy;
	String actual;

	public Assertions(String Actual) {
		this.actual = Actual;
	}
	
	public void AssertTrue(String Excepted,String errorMsg) {
		assertTrue(actual.equals(Excepted),errorMsg);
	}
	
	public void AssertFalse(String Excepted) {
		assertFalse(actual.equals(Excepted));
	}

	
}
