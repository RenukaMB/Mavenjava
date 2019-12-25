package qaframework.pageObject;

import org.openqa.selenium.WebDriver;
import qaframework.webElements.Web_ElementList;

public abstract class Web_PageObject {
	
	public WebDriver wb_driver;
	public Web_ElementList page;
	
	public Web_PageObject(WebDriver _driver) throws Exception{
		this.wb_driver = _driver;
		this.page = new Web_ElementList(this.wb_driver);
	}
	
	public String getTitle() throws Exception {
		return this.wb_driver.getTitle();
	}
}

