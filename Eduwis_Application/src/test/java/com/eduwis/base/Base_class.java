package com.eduwis.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

import com.eduwis.pageObjects.*;

import org.testng.ITestResult;
import org.testng.annotations.*;

//import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.logging.log4j.LogManager;
public class Base_class{
 
	public WebDriver driver;
    protected static ResourceBundle rb;
	public Logger logs;
	public Academics ac;
	public Common_Login_Page clp;
	public DashboardPage dp;
	public StudentAttendance sa;
	public Base_page bp;

	@BeforeClass
	public void start() throws InterruptedException {
		rb = ResourceBundle.getBundle("config");
		String browser = rb.getString("browser");
		logs = LogManager.getLogger(this.getClass());
		try {
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				logs.info("chrome browser is launched ");

			} else if (browser.equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				logs.info("edge browser is launched ");
			} else {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				logs.info(" firefox browser is launched ");
			}
		} catch (Exception e) {
			logs.info(" Invalid driver ");
		}
		driver.manage().window().maximize();
		driver.get(rb.getString("COMMON_URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(3000);
	}
	@BeforeClass(dependsOnMethods="start")
	public void Object_References() {
		   ac = new Academics(driver);
		  clp = new Common_Login_Page(driver);
		   dp = new DashboardPage(driver);
		   sa = new StudentAttendance(driver);
		   bp = new Base_page(driver);
	}
	@AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // If the test method fails, take a screenshot
        	TAKE_SCREENSHOT();
        }
    }

	@AfterClass
	public void close()  {
		driver.close();
		
	}
	
	public String TAKE_SCREENSHOT() {
	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        String timestamp = new SimpleDateFormat("yyyy-MMdd-HH-mm-ss").format(new Date());

	        File fileoutput = ts.getScreenshotAs(OutputType.FILE);
	        String className = this.getClass().getSimpleName(); // Get only the class name without package
	        String destination = System.getProperty("user.dir") + "/screenshots/" + className+"_Failed" + "_" + timestamp + ".png";

	        FileUtils.copyFile(fileoutput, new File(destination));
	        return destination;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // Return null in case of any exception
	    }
	}
	
	}

