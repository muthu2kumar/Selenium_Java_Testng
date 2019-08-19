package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.testng.Assert;

import com.aventstack.extentreports.Status;




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
	 * getTextFromElement - Method returns the text value of the given element
	 * @param driver
	 * @param ele
	 * @return
	 */
	public static String getTextFromElement(WebDriver driver, WebElement ele)
	{
		return ele.getText().trim();
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
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void Click(WebDriver driver, WebElement element)
	{
		element.click();
	}


	/**
	 * 
	 * @param driver
	 * @param element
	 * @param text
	 * @return
	 */
	public static boolean Type(WebDriver driver, WebElement ele, String text)
	{
		boolean status = false;
		WebElement element=waitForElementToBeVisible(driver, ele);
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
	
	/**
	 * 
	 * @param driver
	 * @param by
	 * @param text
	 * @return
	 */
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

	/**
	 * 
	 * @param driver
	 * @param by
	 * @param text
	 * @return
	 */
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

	/**
	 * 
	 * @param driver
	 * @param by
	 * @param text
	 * @return
	 */
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


	
	/**
	 * getElementList - get list of elements using By
	 * @param driver
	 * @param by
	 * @return
	 */
	public static List<WebElement> getElementList(WebDriver driver, By by)
	{
		return driver.findElements(by);
	}

	

	/**
	 * Capture - screenshot capture method
	 * @param driver
	 * @param screenShotName
	 * @return path to the saved screenshot
	 */
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
	 * 
	 * @param by
	 * @return
	 * 
	 */
	public static List<WebElement> getListOfWebElements(WebDriver driver,By by){

		List<WebElement> ele = driver.findElements(by);
		return ele;

	}
	
	
	/**
	 * 
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
	 * 
	 * @param driver
	 * @param ele
	 * @param Name
	 * @throws InterruptedException 
	 */
	public static void clickElementFromListByName(WebDriver driver, List<WebElement> ele, String Name) throws InterruptedException {
		//waitForElementToBeVisible(driver, ele.get(0));
		//Thread.sleep(3000);
		boolean flag = false;
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
	 * 
	 * @param by
	 * @param index
	 * 
	 */
	public static void clickElementFromListByIndex(WebDriver driver,By by, int index) {
		List<WebElement> ele = driver.findElements(by);
		ele.get(index).click();

	}
	
	/**
	 * 
	 * @param driver
	 * @param ele
	 * @param index
	 */
	public static void clickElementFromListByIndex(WebDriver driver,List<WebElement> ele, int index) {
		ele.get(index).click();

	}

	/**
	 * 
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
	 * 
	 * @param driver
	 * @param ele
	 * @param Name
	 * @return index of the element, whose text matches with expected,  in the list
	 * @throws InterruptedException 
	 */
	public static int getItemIndexFromList(WebDriver driver, List<WebElement> ele, String Name) throws InterruptedException {
		Thread.sleep(3000);
		for(int i = 0; i<ele.size();i++) {
			if(ele.get(i).getText().equalsIgnoreCase(Name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
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
	 * 
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
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement ele) {

		Wait waitForElement = new WebDriverWait(driver,60,1000).ignoring(NoSuchElementException.class);
		WebElement element=(WebElement) waitForElement.until(ExpectedConditions.visibilityOf(ele));

		return element;
	}
	
	/**
	 * switchFrame - switches the frame using web element
	 * 
	 * @param driver
	 */
	public static WebDriver switchFrame(WebDriver driver, WebElement ele) {
		return driver.switchTo().frame(ele);
	}
	
	/**
	 * switchFrame - switches the frame using frame id
	 * 
	 * @param driver
	 */
	public static void switchFrame(WebDriver driver, String id) {
		driver.switchTo().frame(id);
	}
	
	/**
	 * switchFrame - switches the frame using frame index
	 * @param driver
	 * @param index
	 */
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
		WebElement ele = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		Logger.log("We think we have hovered mouse on respective elemment");
	}

}
