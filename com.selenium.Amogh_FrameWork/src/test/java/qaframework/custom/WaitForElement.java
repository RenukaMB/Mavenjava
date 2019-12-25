package qaframework.custom;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import qaframework.webElements.WaitTimeConstants;

public class WaitForElement {

	public By webDrivryBy;
	WebElement element;
	WebDriver wb_driver;
	
	public WaitForElement(WebDriver wb_driver) {
		this.wb_driver = wb_driver;
	}
	
	public WebElement getElement(String elementId, String locatorStrategy)
			throws Exception {
		WebElement element = null;
		
		/*switch (locatorStrategy) {
		case "xpath":
			element = wb_driver.findElement(By.xpath(elementId));
			break;
		case "css":
			element = wb_driver.findElement(By.cssSelector(elementId));
			break;
		case "id":
			element = wb_driver.findElement(By.id(elementId));
			break;
		case "name":
			element = wb_driver.findElement(By.name(elementId));
			break;
		case "tagName":
			element = wb_driver.findElement(By.tagName(elementId));
			break;
		case "linktext":
			element = wb_driver.findElement(By.linkText(elementId));
			break;
		}*/
		return element;
	}
	
	public void clickElement(String elementID,String locator)throws Exception{
		element=getElement(elementID, locator);
		element.isDisplayed();
		element.click();
	}
	
	public void enterData(String elementID,String locator,String value)throws Exception{
		element=getElement(elementID,locator);
		element.clear();
		element.sendKeys(value);
		assertEquals(value, element.getAttribute("value"));
	}
	
	public void verifyText(String elementId, String locator, String text)
			throws Exception {
		element = getElement(elementId, locator);
		String strPresent = element.getText();
		assertEquals(text,strPresent);
	}

	public void verifyPageTitle(String title) throws Exception {
		String pagetitle = wb_driver.getTitle();
		assertTrue(pagetitle.contains(title));
	}
	
	public void verifyValue(String elementId, String locator, String value)
			throws Exception {
		element = getElement(elementId, locator);
		String fieldValue = element.getAttribute("value");
		assertEquals(value, fieldValue);
	}
	
	public void selectValue(String elementID, String locator, String value)
			throws Exception {
		element = getElement(elementID, locator);
		new Select(element).selectByVisibleText(value);
		String selectedVal = new Select(element).getFirstSelectedOption()
				.getText();
		assertTrue(selectedVal.equals(value));
	}
	
	public void switchToWindow(String elementId, String locator, String title)
			throws Exception {
		String mainWindowHandle = wb_driver.getWindowHandle();
		clickElement(elementId, locator);
		waitForPageToLoad();
		waitForPageLoding();
		Set<String> win = wb_driver.getWindowHandles();
		Iterator<String> ite = win.iterator();
		while (ite.hasNext()) {
			String popupHandle = ite.next().toString();

			if (!popupHandle.contains(mainWindowHandle)) {
				wb_driver.switchTo().window(popupHandle);

			}
		}
		String Menu = wb_driver.getTitle();
		assertEquals(title, Menu);
	}
	
	public void waitForElement(String elementId, String locator)
			throws Exception {
		int intCnt = 0;
		do {
			try {
				element = getElement(elementId, locator);
				element.isSelected();
				intCnt++;
			} catch (Exception e) {
				intCnt++;
				waitForOneSecond();
			}
		} while (intCnt < 3);
	}

	public void waitForPageToLoad() throws Exception {
		wb_driver.manage().timeouts().implicitlyWait(WaitTimeConstants.WAIT_TIME_LONG, TimeUnit.SECONDS);
		wb_driver.navigate().refresh();
		/*@SuppressWarnings("unused")
		boolean blnPageLoaded;
		do {
			blnPageLoaded = ((JavascriptExecutor) wb_driver).executeScript(
					"return document.readyState").equals("complete");
		} while (blnPageLoaded = false);*/
		
	}
	
	
	public void waitForPageLoding() throws Exception {
		wb_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		/*@SuppressWarnings("unused")
		boolean blnPageLoaded;
		do {
			blnPageLoaded = ((JavascriptExecutor) wb_driver).executeScript(
					"return document.readyState").equals("complete");
		} while (blnPageLoaded = false);*/
	}
	
	
	
	
	public boolean isElementPresent(By by) throws InvocationTargetException{
		@SuppressWarnings("unused")
		int intCnt=0;
		boolean blnFound=false;
		try{				
			try {				
				wb_driver.findElement(by);					
				blnFound =true;
				
			} catch (NoSuchElementException e) {				
				intCnt++;
				waitForOneSecond();	
				blnFound=false;
				
			} catch (StaleElementReferenceException e){
				intCnt++;
				waitForOneSecond();
				blnFound=false;
				
			} catch (InvalidElementStateException e){
				intCnt++;
				waitForOneSecond();
				blnFound=false;
			}
	
		}catch(Exception E){
			
		}
		return blnFound;
	}	
	
	public boolean isElementPresent(By by, int count)
			throws InvocationTargetException{
		wb_driver.manage().timeouts().implicitlyWait(count, TimeUnit.SECONDS);
		@SuppressWarnings("unused")
		int intCnt=0;
		boolean blnFound=false;
		try{				
			try {				
				wb_driver.findElement(by);					
				blnFound =true;
				
			} catch (NoSuchElementException e) {				
				intCnt++;
				waitForOneSecond();		
				blnFound=false;
				
			} catch (StaleElementReferenceException e){
				intCnt++;
				waitForOneSecond();
				blnFound=false;
				
			} catch (InvalidElementStateException e){
				intCnt++;
				waitForOneSecond();
				blnFound=false;
			}
			
		}catch(Exception E){
			
		}
		wb_driver.manage().timeouts().implicitlyWait(WaitTimeConstants.WAIT_TIME_LONG, TimeUnit.SECONDS);
		return blnFound;
	}	
	
	public void waitForElement(String strElement) throws Exception {
		int intCnt = 0;
		do {
			try {
				wb_driver.findElement(By.xpath(strElement)).isDisplayed();
				break;
			} catch (InvalidElementStateException e) {
				intCnt++;
				waitForOneSecond();
			}catch (StaleElementReferenceException SE) {
				intCnt++;
				waitForOneSecond();
			}catch (NoSuchElementException SE) {
				intCnt++;
				waitForOneSecond();
			}catch(Exception e){
				intCnt++;
				waitForOneSecond();
			}
			
		} while (intCnt < 2);
	}
	
	public void waitForElementByID(String strElement) throws Exception {
		int intCnt = 0;
		do {
			try {
				wb_driver.findElement(By.id(strElement)).isDisplayed();
				break;
			} catch (InvalidElementStateException e) {
				intCnt++;
				waitForOneSecond();
			}catch (StaleElementReferenceException SE) {
				intCnt++;
				waitForOneSecond();
			}catch (NoSuchElementException SE) {
				intCnt++;
				waitForOneSecond();
			}catch(Exception e){
				intCnt++;
				waitForOneSecond();
			}
			
		} while (intCnt < 2);
	}
	
	public void waitForElementByName(String strElement) throws Exception {
		int intCnt = 0;
		do {
			try {
				wb_driver.findElement(By.name(strElement)).isDisplayed();
				break;
			} catch (InvalidElementStateException e) {
				intCnt++;
				waitForOneSecond();
			}catch (StaleElementReferenceException SE) {
				intCnt++;
				waitForOneSecond();
			}catch (NoSuchElementException SE) {
				intCnt++;
				waitForOneSecond();
			}catch(Exception e){
				intCnt++;
				waitForOneSecond();
			}
			
		} while (intCnt < 2);
	}
	
	public void waitForLoading(int count) {
		int intCntr = 0;
		wb_driver.manage().timeouts().implicitlyWait(count, TimeUnit.SECONDS);
		try {
			WebElement btn = wb_driver.findElement((By
					.xpath("//div[@id='WaitMessage']")));
			while (intCntr <= 180) {
				try {
					btn.isEnabled();
					btn.isDisplayed();
					intCntr++;
					waitForOneSecond();
				} catch (Exception e) {
					waitForOneSecond();
					break;
				}
			}
		} catch (Exception e) {

		}
		wb_driver.manage().timeouts().implicitlyWait(WaitTimeConstants.WAIT_TIME_SMALL, TimeUnit.SECONDS);
	}
	
	public void waitForElementBycssSelector(String strElement)
			throws Exception {
		int intCnt = 0;
		do {
			try {
				wb_driver.findElement(By.cssSelector(strElement)).isDisplayed();
				break;
			} catch (InvalidElementStateException e) {
				intCnt++;
				waitForOneSecond();
			}catch (StaleElementReferenceException SE) {
				intCnt++;
				waitForOneSecond();
			}catch (NoSuchElementException SE) {
				intCnt++;
				waitForOneSecond();
			}catch(Exception e){
				intCnt++;
				waitForOneSecond();
			}
		} while (intCnt < 2);
	}
	
	public void waitForText(String strElementID, String strText)
			throws Exception {
		int intCnt = 0;
		do {
			try {
				assertTrue(wb_driver.findElement(By.xpath(strElementID)).getText().contains(strText));
				break;
			} catch (AssertionError e) {
				intCnt++;
				waitForOneSecond();
			}
		} while (intCnt < 2);
	}
	
	public void waitForTextByCssSelector(String strElementID, String strText)
			throws Exception {
		int intCnt = 0;
		do {
			try {
				assertTrue(wb_driver.findElement(By.cssSelector(strElementID)).getText().contains(strText));
				break;
			} catch (AssertionError e) {
				intCnt++;
				waitForOneSecond();
			}
		} while (intCnt < 20);
	}
	
	public void waitForGivenSeconds(int intSeconds) throws Exception {
		wb_driver.manage().timeouts().implicitlyWait(intSeconds, TimeUnit.SECONDS);
	}
	
	public void waitForOneSecond() throws Exception {
		Thread.sleep(10000);
//		wb_driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}