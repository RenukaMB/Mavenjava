package page.webPages;

import locators.webLocators.Index;
import org.openqa.selenium.WebDriver;
import qaframework.custom.WaitForElement;
import qaframework.pageObject.Web_PageObject;

public class LoginPageWeb extends Web_PageObject {
	
	WebDriver wb_driver;
	WaitForElement wait;

	public LoginPageWeb(WebDriver _driver) throws Exception {
		super(_driver);
		this.wb_driver=_driver;
		wait = new WaitForElement(this.wb_driver);
	}
	
	public void loginSuccess (String username, String password)throws Exception {
		wb_driver.get(Index.URL);
		this.page.element(Index.USERNAME, "id").waitForVisibilityOfElement();
		this.page.element(Index.USERNAME, "id").getOne().isDisplayed();
		this.page.element(Index.USERNAME, "id").getOne().sendKeys(username);
		this.page.element(Index.PASSWORD, "id").getOne().sendKeys(password);
		this.page.element(Index.LOGIN, "id").getOne().click();
	}
}
 