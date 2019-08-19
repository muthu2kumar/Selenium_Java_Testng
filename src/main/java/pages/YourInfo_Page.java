package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utils.CommonLibrary;

public class YourInfo_Page {

	private WebDriver driver;
	
	/**
	 * pickup location label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"source\"]>div.location-info")
	private WebElement pickUpLocation_Label;
	
	/**
	 * drop location label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"destination\"]>div.location-info")
	private WebElement dropLocation_Label;
	
	/**
	 * pickup date label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"source\"]>div.day-time-info")
	private WebElement pickUpDate_Label;
	
	/**
	 * drop date label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"destination\"]>div.day-time-info")
	private WebElement dropDate_Label;
	
	/**
	 * car type label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div.vehicle-text-info>div.vehicle-name")
	private WebElement carType_Label;
	
	/**
	 * base rate label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"summary\"]>div:nth-child(1)>span.pull-right")
	private WebElement baseRate_Label;
	
	/**
	 * mileage label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"summary\"]>div:nth-child(2) span[class*=\"pull-right\"]")
	private WebElement mileage_Label;
	
	/**
	 * Fees & Taxes label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"summary\"]>div:nth-child(5)>span.pull-right")
	private WebElement feesTaxes_Label;
	
	/**
	 * Estimated total price label in header
	 */
	@FindBy(css = "div.coupanCInfo.summary-container.collapse-mode div[class*=\"summary\"]>div:nth-child(6)>span.pull-right")
	private WebElement totalRate_Label;
	
	/**
	 * First name text ox in user information form
	 */
	@FindBy(id = "firstname")
	private WebElement firstName_Txt;
	
	/**
	 * Last name text ox in user information form
	 */
	@FindBy(id = "lastname")
	private WebElement lastName_Txt;
	
	
	
	/**
	 * Constructor
	 * @param driver
	 */
	
	public YourInfo_Page(WebDriver driver) {
		AjaxElementLocatorFactory finder =  new AjaxElementLocatorFactory(driver, 15);
		PageFactory.initElements(finder, this);
		this.driver=driver;
	}
	
	RentalOptions_Page rentalOptions = new RentalOptions_Page(driver);
	
	/**
	 * verifyYourInfoIsDisplayed - verifies that user info page is displayed or not
	 * @return boolean
	 */
	public boolean verifyYourInfoIsDisplayed() {
		return firstName_Txt.isDisplayed();
	}
	
	public String getPickUpLocation() {
		return CommonLibrary.getTextFromElement(driver, pickUpLocation_Label);
	}
	
	public String getDropdLocation() {
		return CommonLibrary.getTextFromElement(driver, dropLocation_Label);
	}
	
	public String getPickUpDate() {
		return CommonLibrary.getTextFromElement(driver, pickUpDate_Label);
	}
	
	public String getDropDate() {
		return CommonLibrary.getTextFromElement(driver, dropDate_Label);
	}
	
	public String getCarType() {
		return CommonLibrary.getTextFromElement(driver, carType_Label);
	}
	
	public String getBaseRate() {
		return CommonLibrary.getTextFromElement(driver, baseRate_Label);
	}
	
	public String getMileage() {
		return CommonLibrary.getTextFromElement(driver, mileage_Label);
	}
	
	public String getFeesTaxes() {
		return CommonLibrary.getTextFromElement(driver, feesTaxes_Label);
	}
	
	public String getTotalRate() {
		return CommonLibrary.getTextFromElement(driver, totalRate_Label);
	}
	
	
}
