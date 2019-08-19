package tests.SelectCar;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.Status;

import pages.RentalOptions_Page;
import pages.Search_Page;
import pages.SelectCar_Page;
import pages.YourInfo_Page;
import utils.BaseDriver;
import utils.ExtentTestManager;

@Listeners(utils.TestListener.class)
public class TEST_user_books_suv_from_immediate_monday_for_7_days extends BaseDriver {
	Assertion hardAssert = new Assertion();

	@Test(description = "user tries to book a car from immediate monday for 7 days")
	public void user_books_suv_from_immediate_monday_for_7_days() throws InterruptedException {
		
		try {
			/*
			 * Data
			 */
			String pickUpLocation = "Austin Bergstrom Intl Airport";
			String dropLocation = "Austin Straubel Intl Airport";
			String carType = "Intermediate Suv";
			String baseRate;
			String milage;
			String feesAndTaxes;
			String totalEstimate;
			
			/*
			 * pages
			 */
			Search_Page search = new Search_Page(getDriver());
			SelectCar_Page selectCar = new SelectCar_Page(getDriver());
			RentalOptions_Page rentalOptions = new RentalOptions_Page(getDriver());
			YourInfo_Page userInfo = new YourInfo_Page(getDriver());
			
			/*
			 * test
			 */
			launchApp("BUDGET");
			
			search.enterPickUpLocation(pickUpLocation);
			search.enterDropLocation(dropLocation);
			search.chooseMondayToSunday();
			search.clickSearchButton();
			
			baseRate = selectCar.getPrice(carType);
			selectCar.chooseCarType(carType);
			
			hardAssert.assertTrue(rentalOptions.getPickUpLocation().contains(pickUpLocation));
			hardAssert.assertTrue(rentalOptions.getDropdLocation().contains(dropLocation));
			hardAssert.assertTrue(rentalOptions.getBaseRate().replace("\n", "").contains(baseRate));
			hardAssert.assertTrue(rentalOptions.getCarType().contains(carType));
			milage = rentalOptions.getMileage();
			feesAndTaxes = rentalOptions.getFeesTaxes();
			totalEstimate = rentalOptions.getTotalRate();
			rentalOptions.clickContinueButton();
			
			userInfo.verifyYourInfoIsDisplayed();
			hardAssert.assertTrue(userInfo.getPickUpLocation().contains(pickUpLocation));
			hardAssert.assertTrue(userInfo.getDropdLocation().contains(dropLocation));
			hardAssert.assertTrue(userInfo.getBaseRate().replace("\n", "").contains(baseRate));
			hardAssert.assertTrue(userInfo.getCarType().contains(carType));
			hardAssert.assertTrue(userInfo.getMileage().contains(milage));
			hardAssert.assertTrue(userInfo.getFeesTaxes().contains(feesAndTaxes));
			hardAssert.assertTrue(userInfo.getTotalRate().contains(totalEstimate));
			
		} catch (Exception e) {
			e.printStackTrace();
			String eMessage = e.getMessage();
			ExtentTestManager.getTest().log(Status.WARNING, eMessage);
			hardAssert.assertTrue(false, "Something went wrong. Code reached catch block. Please refer stack trace for more info." + eMessage);
		}
	}
}
