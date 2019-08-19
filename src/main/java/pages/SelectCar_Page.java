package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utils.CommonLibrary;

public class SelectCar_Page {
	
	private WebDriver driver;
	
	/**
	 * Car type label
	 */
	@FindBy(css = "div.step2dtl-avilablecar-section h3")
	private List<WebElement> carType_Label;
	
	/**
	 * Price label
	 */
	@FindBy(css = "div.step2dtl-avilablecar-section price")
	private List<WebElement> price_Label;
	
	/**
	 * Select car button
	 */
	@FindBy(id = "res-vehicles-select")
	private List<WebElement> selectCar_Button;
	
	/**
	 * Constructor
	 * @param driver
	 */
	
	public SelectCar_Page(WebDriver driver) {
		AjaxElementLocatorFactory finder =  new AjaxElementLocatorFactory(driver, 15);
		PageFactory.initElements(finder, this);
		this.driver=driver;
	}
	
	public void chooseCarType(String carType) throws InterruptedException {
		int carTypeIndex = CommonLibrary.getItemIndexFromList(driver, carType_Label, carType);
		CommonLibrary.clickElementFromListByIndex(driver, selectCar_Button, carTypeIndex);
	}
	
	public String getPrice(String carType) throws InterruptedException {
		int carTypeIndex = CommonLibrary.getItemIndexFromList(driver, carType_Label, carType);
		return price_Label.get(carTypeIndex).getText();
	}
	
	
	
}
