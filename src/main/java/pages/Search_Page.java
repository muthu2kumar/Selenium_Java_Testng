package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonLibrary;

public class Search_Page {

	private WebDriver driver;
	
	/**
	 * Pick up location text box
	 */
	@FindBy(id = "PicLoc_value")
	private WebElement pickUpLocation_Txt;
	
	/**
	 * Drop up location text box
	 */
	@FindBy(id = "DropLoc_value")
	private WebElement dropLocation_Txt;
	
	/**
	 * Pick up location dropdown
	 */
	By pickUpLocation_DropDown = By.cssSelector("div#PicLoc_dropdown div.angucomplete-results>div>div span.angucomplete-result-name.ng-binding.ng-scope");
	
	/**
	 * Drop location dropdown
	 */
	By dropLocation_DropDown = By.cssSelector("div#DropLoc_dropdown div.angucomplete-results>div>div span.angucomplete-result-name.ng-binding.ng-scope");
	
	/**
	 * Constructor
	 * @param driver
	 */
	
	public Search_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void enterPickUpLocation(String pickUpLocation) {
		CommonLibrary.Type(driver, pickUpLocation_Txt, pickUpLocation);
		CommonLibrary.clickElementFromListByName(driver, pickUpLocation_DropDown, pickUpLocation);
	}
	
	public void enterDropLocation(String dropLocation) {
		CommonLibrary.Type(driver, dropLocation_Txt, dropLocation);
		CommonLibrary.clickElementFromListByName(driver, dropLocation_DropDown, dropLocation);
	}
	
	
}
