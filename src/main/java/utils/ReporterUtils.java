package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ReporterUtils {
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
       
       
       public static void startReport()
       {
           System.out.println("Inside reports");
           Date d = new Date();
           SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy_HH.mm");
           String date = sf.format(d);
           htmlReporter = new ExtentHtmlReporter(System.getenv("WORKING_DIRECTORY") +"/TestReport"+date+".html");
           extent = new ExtentReports();
           extent.attachReporter(htmlReporter);
         
            
           htmlReporter.config().setChartVisibilityOnOpen(true);
           htmlReporter.config().setDocumentTitle("Automation Report");
           htmlReporter.config().setReportName("Shop Andriod - Automation report");
           htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
           htmlReporter.config().setTheme(Theme.DARK);
           System.out.println("report instance is created"+htmlReporter.toString());
       }
       public static void create(String testName)
       {
           test = extent.createTest(testName);
       }
       public static void pass(String Pass)
       {
           
           test.log(Status.PASS, MarkupHelper.createLabel(Pass, ExtentColor.GREEN)); 
           extent.flush();
       }
       public static void info(String info)
       {
       		test.log(Status.INFO, MarkupHelper.createLabel(info, ExtentColor.BLUE));
       		extent.flush();
       }
       public static void info(Object obj)
       {
       		test.log(Status.INFO, MarkupHelper.createLabel(obj.toString(), ExtentColor.BLUE));
       		extent.flush();
       }
       
       public static void fail(String testCaseName, String screenshotName, String exceptionMsg)
       {
           try {
            test.log(Status.FAIL, MarkupHelper.createLabel(testCaseName, ExtentColor.RED));
            
            if(!exceptionMsg.isEmpty())
                test.fail(exceptionMsg);
            test.info("Screenshot below", MediaEntityBuilder.createScreenCaptureFromPath(CommonLibrary.Capture(BaseDriver.getDriver(), screenshotName)).build());
           
               } catch (IOException e1) {
                   
               
               }
               extent.flush();
       }
}
