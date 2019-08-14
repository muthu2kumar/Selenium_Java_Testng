package tests.Login;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.Status;

import pages.Header_Page;
import pages.Login_Page;
import pages.Search_Page;
import utils.BaseDriver;
import utils.DateUtils;
import utils.ExtentTestManager;

@Listeners(utils.TestListener.class)
public class TEST_login_invalid_credentails extends BaseDriver {
	Assertion hardAssert = new Assertion();

	@Test(description = "user tries to login using incorrect id and password")
	public void user_tries_invalid_id_password_Test() throws InterruptedException {
		
		try {
			/*
			 * Data
			 */
			String pickUpLocation = "Austin Bergstrom Intl Airport";
			String dropLocation = "Austin Straubel Intl Airport";
			
			/*
			 * pages
			 */
			Search_Page search = new Search_Page(getDriver());
			
			
			/*
			 * test
			 */
			//launchApp("BUDGET");
			//Thread.sleep(5000);
			
			//search.enterPickUpLocation(pickUpLocation);
			//search.enterDropLocation(dropLocation);
			
			System.out.println(DateUtils.nextOrSame("MONDAY"));
			
		} catch (Exception e) {
			e.printStackTrace();
			String eMessage = e.getMessage();
			ExtentTestManager.getTest().log(Status.WARNING, eMessage);
			hardAssert.assertTrue(false, "Something went wrong. Code reached catch block. Please refer stack trace for more info." + eMessage);
		}
	}
}
