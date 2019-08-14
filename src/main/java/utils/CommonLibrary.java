package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.seleniumhq.jetty7.util.log.Log;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import locators.Header_Locators;



@SuppressWarnings({"unchecked", "rawtypes"})
public class CommonLibrary {
	public static WebDriver driver;
	static Dimension size;

	
	/**
	 * getTextFromElement - Method returns the text value of the given element
	 * @param by
	 * @return
	 */
	public static String getTextFromElement(WebDriver driver, By by)
	{
		return driver.findElement(by).getText().trim();
	}
	
	/**
	 * Click - waits for the element to be visible then proceeds to click on the element
	 * @param driver
	 * @param by
	 */
	public static void Click(WebDriver driver, By by)
	{
		WebElement element=waitForElementToBeVisible(driver, by);

		element.click();
	}

	public static void scrollDown()
	{
		//Scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;

		HashMap scrollObject = new HashMap();

		scrollObject.put("direction", "down");

		js.executeScript("mobile: scroll", scrollObject);
	}

	public static boolean Type(WebDriver driver, WebElement element, String text)
	{
		boolean status = false;
		
		element.click();
		element.sendKeys(text);
		if(element.getText().equals(text))
		{
			status = true;
			Logger.log("Typed "+text+" in the textbox");
		}
		else
		{
			status = false;
			Logger.log("The "+text+" not typed in the textbox");
		}

		return status;
	}
	
	public static boolean Type(WebDriver driver, By by, String text)
	{
		boolean status = false;
		WebElement element=waitForElementToBeVisible(driver, by);

		element.click();
		element.sendKeys(text);
		if(element.getText().equals(text))
		{
			status = true;
			Logger.log("Typed "+text+" in the textbox");
		}
		else
		{
			status = false;
			Logger.log("The "+text+" not typed in the textbox");
		}

		return status;
	}

	public static boolean verifyText(WebDriver driver, By by, String text)
	{
		boolean status = false;
		Wait waitForElement = new FluentWait(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));

		while(!status)
		{
			if(driver.findElements(by).size() > 0 && driver.findElement(by).isDisplayed())
				status = true;
		}

		status = false;

		if(element.getText().equals(text))
			status = true;

		return status;
	}

	public static boolean clearAndType(WebDriver driver, By by, String text) {

		boolean status = false;

		Wait waitForElement = new FluentWait(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));

		while(!status)
		{
			if(driver.findElements(by).size() > 0 && driver.findElement(by).isDisplayed())
				status = true;
		}


		element.clear();
		element.sendKeys(text);
		if(element.getText().equals(text))
		{
			status = true;
			Logger.log("Typed "+text+" in the textbox");
		}
		else
		{
			status = false;
			Logger.log("The "+text+" not typed in the textbox");
		}

		return status;
	}


	/*	public static File getscreenshot(AppiumDriver driver, String screenShotName) 
		{
			File scrFile = null;
			try {
				scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(constants.Generic_Constants.screenshotFolderName+"/"+screenShotName+".png"));
				Logger.log("Screenshot taken");
			}
			catch(Exception e)
			{
				Logger.exceptionMsg = e.getMessage();
				Logger.log("Exception while taking screenshot");
			}

			return scrFile;
		}
	 */


	/*	public static void enter(AppiumDriver driver) {

			try {
				((AndroidDriver) driver).pressKeyCode(66);
				Logger.log("Pressed Enter Key");
			}

			catch(Exception e)
			{
				Logger.exceptionMsg = e.getMessage();
				Logger.log("Exception while pressing Enter Key");
			}
		}*/


	public static boolean isElementPresent(WebDriver driver, By by)
	{
		boolean status = false;
		if(driver.findElements(by).size() > 0)
			status = true;
		return status;
	}
	public static List<WebElement> getElementList(WebDriver driver, By by)
	{
		return driver.findElements(by);
	}

	public static void assertTextNativeApp(WebElement element, String Text) throws Exception {
		try {

			String ActualText = element.getText();
			Assert.assertEquals(ActualText,Text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void assertAttributeValueNativeApp(WebElement element, String attribute ,String Text) throws Exception {
		try {

			String ActualText = element.getAttribute(attribute);
			Assert.assertEquals(ActualText,Text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clickwait(WebDriver driver)
	{
		boolean status = true;
		while(status)
		{
			if(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"))
				status=false;
		}  
	}


	public static String getAttributeValueNativeApp(WebElement element,String attribute) throws Exception {
		String text = null;
		try {

			text = element.getAttribute(attribute);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}



	public static String[] getDataSet(List<String[]> table, String text)
	{
		String[] dSet = null;
		for(int i = 0 ; i < table.size(); i++)
		{
			System.out.println(table.get(i)[0]);
			if(table.get(i)[0].equals(text))
			{
				dSet = new String[table.get(i).length];
				for(int j=0 ; j < table.get(i).length;j++)
					dSet[j] = table.get(i)[j];
				break;
			}
		}
		return dSet;
	}


	public static void clickBtn(String text)
	{
		List<WebElement> d = driver.findElements(By.tagName("button"));

		for(WebElement e : d)
		{
			if(e.getText().contains(text))
				e.click();
		}
	}

	public static String Capture(WebDriver driver, String screenShotName) 
	{ 	

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		//String dest = "./ShopApp_Screenshot"+screenShotName+dateName+".png";
		String dest = System.getProperty("user.dir") + System.getProperty("file.separator") + "TestReport" 
						+ System.getProperty("file.separator") + "screenshots" + System.getProperty("file.separator") 
						+ screenShotName+dateName+".png";

		Logger.log("Screenshot taken");
		File destination = new File(dest);
		try {
			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;


	}

	/**
	 * @author sds-v.muthu
	 * @param by
	 * @return
	 * 
	 */
	public static List<WebElement> getListOfWebElements(WebDriver driver,By by){

		List<WebElement> ele = driver.findElements(by);
		return ele;

	}
	
	
	/**
	 * @author sds-v.muthu
	 * @param by
	 * @param Name
	 */
	public static void clickElementFromListByName(WebDriver driver, By by, String Name) {
		CommonLibrary.waitForElementToBeVisible(driver, by);
		boolean flag = false;
		List<WebElement> ele = driver.findElements(by);
		for(WebElement e:ele) {
			//String eText = e.getText().trim();
			if(e.getText().trim().equalsIgnoreCase(Name)) {
				e.click();
				flag = true;
				break;
			}
		}

		if(flag) {
			Logger.log("We think we have clicked the " + Name + " tab");
		}else {
			Assert.assertTrue(flag, "Expected value: " + Name + " not found in the list");
		}
	}

	/**
	 * @author sds-v.muthu
	 * @param by
	 * @param index
	 * 
	 */
	public static void clickElementFromListByIndex(WebDriver driver,By by, int index) {
		List<WebElement> ele = driver.findElements(by);
		ele.get(index).click();

	}

	/**
	 * @author sds-v.muthu
	 * @param by
	 * @param Name
	 * @return
	 */
	public static int getItemIndexFromList(WebDriver driver, By by, String Name) {
		List<WebElement> ele = driver.findElements(by);
		for(int i = 0; i<ele.size();i++) {
			if(ele.get(i).getText().equalsIgnoreCase(Name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @author sds-v.muthu
	 * @param driver
	 * @param by
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver, By by) {
		boolean status = false;

		Wait waitForElement = new WebDriverWait(driver,60,1000).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.elementToBeClickable(by));

		while(!status)
		{
			if(driver.findElements(by).size() > 0 && driver.findElement(by).isDisplayed() && element.isDisplayed())
				status = true;
		}
		return element;
	}
	
	/**
	 * @author sds-v.muthu
	 * @param driver
	 * @param by
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, By by) {
		boolean status = false;

		Wait waitForElement = new WebDriverWait(driver,60,1000).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));

		while(!status)
		{
			if(driver.findElements(by).size() > 0 && driver.findElement(by).isDisplayed() && element.isDisplayed())
				status = true;
		}
		return element;
	}
	
	/**
	 * changeDriverContextToWeb - switches the context from Native to Web
	 * @author sds-v.muthu
	 * @param driver
	 */
	public static WebDriver switchFrame(WebDriver driver, WebElement ele) {
		return driver.switchTo().frame(ele);
	}
	
	/**
	 * changeDriverContextToNative - switches the context from Web to Native
	 * @author sds-v.muthu
	 * @param driver
	 */
	public static void switchFrame(WebDriver driver, String id) {
		driver.switchTo().frame(id);
	}
	
	public static void switchFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	
	/**
	 * waitAndClick - waits for the element to be clickable then proceeds to click on the element
	 * @param driver
	 * @param by
	 */
	public static void waitAndClick(WebDriver driver, By by)
	{
		boolean status = false;

		Wait waitForElement = new WebDriverWait(driver,60,1000).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.elementToBeClickable(by));

		while(!status)
		{
			if(driver.findElements(by).size() > 0 && driver.findElement(by).isDisplayed() && element.isDisplayed())
				status = true;
		}
		element.click();
	}
	
	/**
	 * jsClick - Clicks on element using javascript executor
	 * @param driver
	 * @param element
	 */
	public static void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * waitForVisibleAndClick - waits for the element to be visible then proceeds to click on the element
	 * @param driver
	 * @param by
	 */
	public static void waitForVisibleAndClick(WebDriver driver, By by)
	{
		boolean status = false;

		Wait waitForElement = new WebDriverWait(driver,60,1000).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));

		while(!status)
		{
			if(driver.findElements(by).size() > 0 && driver.findElement(by).isDisplayed() && element.isDisplayed())
				status = true;
		}
		element.click();
	}
	
	/**
	 * waitForElementToDisappeared - waits till the element disappears or the timeout expires
	 * @param driver
	 * @param by
	 */
	public static void waitForElementToDisappear(WebDriver driver, By by) {
		Wait waitForElement = new WebDriverWait(driver,60);
		waitForElement.until(ExpectedConditions.invisibilityOfElementLocated(by));
		ExtentTestManager.getTest().log(Status.PASS, "We think loader block has disappeared");
	}

	/**
	 * isDisplayed - returns boolean on whether element is displayed or not
	 * @param driver
	 * @param by
	 */
	public static boolean isDisplayed(WebDriver driver, By by) {
		Wait waitForElement = new WebDriverWait(driver,60);
		waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by).isDisplayed();
	}
	
	/**
	 * hoverOver - performs mouse hover over an element
	 * @param driver
	 * @param by
	 */
	public static void hoverOver(WebDriver driver, By by) {
		Wait waitForElement = new WebDriverWait(driver,60);
		waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement ele = driver.findElement(Header_Locators.loginMenu);
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		Logger.log("We think we have hovered mouse on respective elemment");
	}

}
