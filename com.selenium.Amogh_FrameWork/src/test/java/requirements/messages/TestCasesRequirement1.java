package requirements.messages;

import org.testng.annotations.Test;

import configuration.WebConfiguration;
import data.webData.IndexData;
import page.webPages.LoginPageWeb;
import qaframework.custom.DateAndTimeFunctions;


public class TestCasesRequirement1 extends WebConfiguration{
	
	static DateAndTimeFunctions dateAndTimeFunctions = new DateAndTimeFunctions();
	
	@Test()
	void requirement01() throws Exception {
		System.out.println("Welcome to the Selenium FrameWork");
		
		LoginPageWeb login = new LoginPageWeb(wb_driver);
		login.loginSuccess(IndexData.USERNAME, IndexData.PASSWORD);
		
		
	}

  }

	
