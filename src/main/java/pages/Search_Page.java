package pages;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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
	@FindBy(css = "div#PicLoc_dropdown div.angucomplete-results>div>div span.angucomplete-result-name.ng-binding.ng-scope")
	private List<WebElement> pickUpLocation_DropDown;
	//By pickUpLocation_DropDown = By.cssSelector("div#PicLoc_dropdown div.angucomplete-results>div>div span.angucomplete-result-name.ng-binding.ng-scope");
	
	/**
	 * Drop location dropdown
	 */
	@FindBy(css = "div#DropLoc_dropdown div.angucomplete-results>div>div span.angucomplete-result-name.ng-binding.ng-scope")
	private List<WebElement> dropLocation_DropDown;
	//By dropLocation_DropDown = By.cssSelector("div#DropLoc_dropdown div.angucomplete-results>div>div span.angucomplete-result-name.ng-binding.ng-scope");
	
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
	@FindBy(css = "table.ui-datepicker-calendar.uitable.ui-datepicker-table-first td>a")
	private List<WebElement> currentMonth_Calendar;
	//By currentMonth_Calendar = By.cssSelector("table.ui-datepicker-calendar.uitable.ui-datepicker-table-first td>a");
	
	/**
	 * next month's calendar field
	 */
	@FindBy(css = "table.ui-datepicker-calendar.uitable.ui-datepicker-table-last td>a")
	private List<WebElement> nextMonth_Calendar;
	//By nextMonth_Calendar = By.cssSelector("table.ui-datepicker-calendar.uitable.ui-datepicker-table-last td>a");
	
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
		
		AjaxElementLocatorFactory finder =  new AjaxElementLocatorFactory(driver, 15);
		PageFactory.initElements(finder, this);
		this.driver=driver;
	}
	
	public void enterPickUpLocation(String pickUpLocation) throws InterruptedException {
		CommonLibrary.Type(driver, pickUpLocation_Txt, pickUpLocation);
		CommonLibrary.clickElementFromListByName(driver, pickUpLocation_DropDown, pickUpLocation);
	}
	
	public void enterDropLocation(String dropLocation) throws InterruptedException {
		CommonLibrary.Type(driver, dropLocation_Txt, dropLocation);
		CommonLibrary.clickElementFromListByName(driver, dropLocation_DropDown, dropLocation);
	}
	
	public void chooseMondayToSunday() throws InterruptedException {
		
		LocalDate ld = DateUtils.nextOrSame("MONDAY");
		int startDate = ld.getDayOfMonth();
		int startMonth = ld.getMonthValue();
		chooseStartDate(startDate,startMonth);
		
		int endDate = ld.plusDays(7).getDayOfMonth();
		int endMonth = ld.plusDays(7).getMonthValue();
		chooseEndDate(endDate, endMonth);
		
		
	}
	
	public void chooseStartDate(int date, int month) throws InterruptedException {
		int currentMonth = DateUtils.getCurrentMonth();
		CommonLibrary.Click(driver, pickUp_DateField);
		if(currentMonth == month) {
			CommonLibrary.clickElementFromListByName(driver, currentMonth_Calendar, Integer.toString(date));
		}else {
			CommonLibrary.clickElementFromListByName(driver, nextMonth_Calendar, Integer.toString(date));
		}
	}
	
	public void chooseEndDate(int date, int month) throws InterruptedException {
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
