/**
 * 
 */
package newpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * @author RENUKA
 *
 */
public class RegistrationPage {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\SeleniumJAR\\firefox\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='text' and @name='username']")).sendKeys("renukagowdajs@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys("Passwordtab1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea[@cols='40' and @name='comments']")).sendKeys("Hello Hai Hello Hai");
		Thread.sleep(3000);
		WebElement elem = driver.findElement(By.xpath("//input[@type='file']"));
		elem.sendKeys("C:\\Users\\RENUKA\\Desktop\\New folder (2)\\sig.jpg");

		Thread.sleep(3000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @ value='cb3']")).isSelected(),"Error info:check box not selected");
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//option[@value='ms4']")).isSelected(),"Error info:check box not selected");
		Thread.sleep(3000);
		Select text=new Select(driver.findElement(By.xpath("//select[@name='dropdown']")));
		text.selectByVisibleText("Drop Down Item 1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='submit' and @name='submitbutton']")).click();


	}

}
