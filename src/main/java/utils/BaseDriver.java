package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.Constants;
import utils.Logger;

public class BaseDriver {

	public static String platformVersion = "";
	public static String deviceId = "";
	public static String platformName = "";
	public static String deviceName = "";
	public static String app = "";
	public static String appPackage = "";
	public static String appActivity = "";
	public static String browserName = "";
	public static boolean aemRequired = true;

	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<> ();
	@BeforeMethod
	public static void initDriver() throws InterruptedException, MalformedURLException {

		platformVersion = System.getProperty("platformVersion");
		deviceId = System.getProperty("deviceId");
		platformName = System.getProperty("platformName");
		deviceName = System.getProperty("deviceName");
		browserName = System.getProperty("browserName");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (platformName!=null && platformName.equalsIgnoreCase("Android")) {
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("udid", deviceId);
			capabilities.setCapability("platformName", platformName);
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("newCommandTimeout", 3000);
			capabilities.setCapability("browserName", "chrome");
			driver.set(new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),capabilities));

		}else if (platformName!=null && platformName.equalsIgnoreCase("Mac") && deviceName!=null) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
			Map<String, String> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", deviceName);

			//Set BrowserName

			capabilities.setCapability("browserName", browserName);

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			chromeOptions.addArguments("--incognito");

			driver.set(new ChromeDriver(chromeOptions));
		}else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
			
			capabilities.setCapability("browserName", browserName);

			ChromeOptions chromeOptions = new ChromeOptions();

			driver.set(new ChromeDriver(chromeOptions));

			getDriver().manage().window().maximize();
		}

	}

	public static WebDriver getDriver() {
		//Get driver from ThreadLocalMap
		return driver.get();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@AfterClass void terminate () {
		//Remove the ThreadLocalMap element
		driver.remove();
	}

	public void launchApp(String appName) {
		String URL = getURL(appName);
		getDriver().get(URL);

		Logger.log("We think we have launched " + appName + " in browser");
		ExtentTestManager.getTest().log(Status.PASS, "We think we have launched " + appName + " in browser");
	}

	public String getEnv() {
		String Env = Constants.APP_ENV;
		if(Env!=null) {
			Env=Env.toUpperCase();
		}else {
			Env = "PROD";
		}
		return Env;
	}

	public String getURL(String appName) {
		String Env = getEnv();
		switch(Env) {
		case "QA":
			switch(appName) {
			case "BUDGET":
				return "https://www.budget.com/en/home";
			}

		case "PROD":
			switch(appName) {
			case "BUDGET":
				return "https://www.budget.com/en/home";
			}

		default: 
			return "";
		}
	}

}


