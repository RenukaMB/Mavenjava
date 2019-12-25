package configuration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import qaframework.webElements.WaitTimeConstants;



public class WebConfiguration {  

	private String browser;
	public WebDriver wb_driver;

	@BeforeMethod (alwaysRun=true)
	public WebDriver setupWeb() throws Exception {
		browser = "chrome";
		DesiredCapabilities cd = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", System.getProperty("webdriver.chrome.driver", "C:\\SeleniumJAR\\chrome\\chromedriver.exe"));
		cd.setBrowserName(browser);
		wb_driver = new ChromeDriver(cd);
		wb_driver.manage().deleteAllCookies();
		wb_driver.manage().timeouts()
		         .implicitlyWait(WaitTimeConstants.WAIT_TIME, TimeUnit.SECONDS)
		         .pageLoadTimeout(WaitTimeConstants.WAIT_TIME_LONG,TimeUnit.SECONDS)
		         .setScriptTimeout(WaitTimeConstants.WAIT_TIME_LONG, TimeUnit.SECONDS);
		wb_driver.manage().window().maximize();
		return wb_driver; 
	}

	@AfterMethod(alwaysRun=true)
	public void tearDownWeb() throws Exception {
		wb_driver.quit();
	}

}