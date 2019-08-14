package pages;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonLibrary;
import utils.DateUtils;

public class Search_Page {

	private WebDriver driver;
	
	/**
	 * Pick up location text box
	 */
	@FindBy(id = "PicLoc_value")
	private WebElement pickUpLocation_Txt;
	
	/**
	 * Drop location text box
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
	 * pickup date field
	 */
	@FindBy(name = "reservationModel.pickUpDateDisplay")
	private WebElement pickUp_DateField;
	
	/**
	 * drop date field
	 */
	@FindBy(name = "reservationModel.dropDateDisplay")
	private WebElement drop_DateField;
	
	/**
	 * current month's calendar field
	 */
	By currentMonth_Calendar = By.cssSelector("table.ui-datepicker-calendar.uitable.ui-datepicker-table-first td>a");
	
	/**
	 * next month's calendar field
	 */
	By nextMonth_Calendar = By.cssSelector("table.ui-datepicker-calendar.uitable.ui-datepicker-table-last td>a");
	
	/**
	 * Search car button
	 */
	@FindBy(id = "res-home-select-car")
	private WebElement search_Button;
	
	
	
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
	
	public void chooseMondayToSunday() {
		
		LocalDate ld = DateUtils.nextOrSame("MONDAY");
		int startDate = ld.getDayOfMonth();
		int startMonth = ld.getMonthValue();
		chooseStartDate(startDate,startMonth);
		
		int endDate = ld.plusDays(7).getDayOfMonth();
		int endMonth = ld.plusDays(7).getMonthValue();
		chooseEndDate(endDate, endMonth);
		
		
	}
	
	public void chooseStartDate(int date, int month) {
		int currentMonth = DateUtils.getCurrentMonth();
		CommonLibrary.Click(driver, pickUp_DateField);
		if(currentMonth == month) {
			CommonLibrary.clickElementFromListByName(driver, currentMonth_Calendar, Integer.toString(date));
		}else {
			CommonLibrary.clickElementFromListByName(driver, nextMonth_Calendar, Integer.toString(date));
		}
	}
	
	public void chooseEndDate(int date, int month) {
		int currentMonth = DateUtils.getCurrentMonth();
		CommonLibrary.Click(driver, drop_DateField);
		if(currentMonth == month) {
			CommonLibrary.clickElementFromListByName(driver, currentMonth_Calendar, Integer.toString(date));
		}else {
			CommonLibrary.clickElementFromListByName(driver, nextMonth_Calendar, Integer.toString(date));
		}
	}
	
	public void clickSearchButton() {
		CommonLibrary.Click(driver, search_Button);
	}
	
}
