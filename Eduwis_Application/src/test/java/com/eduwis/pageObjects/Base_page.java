package com.eduwis.pageObjects;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_page {
	public WebDriver driver;
	public static Logger log;
	public ResourceBundle rbs;

	public Base_page(WebDriver driver) {

		// In this constructor we call the web driver and logs in the same constructor
		// and config file
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log = LogManager.getLogger(this.getClass());// logs
		rbs = ResourceBundle.getBundle("config");// properties file
	}

	// dropdown to select an element by index value
	public static void SelectbyIndexValue(WebElement index_ele, int index) {
		Select sc = new Select(index_ele);
		if (index_ele.isDisplayed()) {
			sc.selectByIndex(index);
			System.out.println("element is selected by using index value");
		} else {
			System.out.println("element is not selected");
		}

	}

	// dropdown to select an element by visible text
	public static void SelectByVisibleText(WebElement visible_text_ele, String text) {
		Select sc = new Select(visible_text_ele);
		if (visible_text_ele.isDisplayed()) {
			sc.selectByVisibleText(text);
			System.out.println("element is slected ");
		} else {
			System.out.println("element is not selected");
		}
	}
	// dropdown to select an element by value

	public static void SelectByValue(WebElement value_ele, String value) {
		Select sc = new Select(value_ele);
		if (value_ele.isDisplayed()) {
			sc.selectByValue(value);

			System.out.println("element is slected ");
		} else {
			System.out.println("element is not selected");

		}
	}

	// scrool bar using action class
	public void SCROLLBAR(WebElement ele) {
		Actions act = new Actions(driver);
		try {
			act.clickAndHold(ele).moveByOffset(0, 200).release().perform();
		} catch (Exception e) {
			log.info("Scroll action not performend" + e.getMessage());
		}
	}

//SCROLL BAR USING WEB ELEMENTS 
	public void SCROLLBAR(WebElement ele, WebElement ele2) {
		try {
			Actions act = new Actions(driver);
			act.clickAndHold(ele).moveToElement(ele2).release().build().perform();
		} catch (Exception e) {
			log.info("Scroll action not performend" + e.getMessage());
		}
	}

	//SCREENSHOT METHOD
	public String capturescreenshort(String tname) throws IOException {
		// time stramp

		String timestramp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\Screen_shorts\\" + tname + "_" + timestramp + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
//TIME WAIT GOR THE ELEMENT 
	public void WAIT_FOR_THE_ELEMENT(WebElement ele, int time) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			log.info("element not visible");
		}
	}
	//DatePickers
	 @FindBy(id="date")private WebElement datePickerInput ;
	 @FindBy(xpath="(//th[@class='datepicker-switch'])[1]")private WebElement Month_Year_Btn;
	 @FindBy(xpath="//tr/td[@class='active day' or @class='day']")private List<WebElement> Dates;
	 @FindBy(xpath="(//th[@class='datepicker-switch'])[2]")private WebElement Year_Btn;
	 @FindBy(xpath="//span[@class='month' or @class='month active']")private List<WebElement> Months;
	 @FindBy(xpath="//span[contains(@class,'year')]")private List<WebElement> Years;
	 
	 public void selectDate(String targetYear, String targetMonth, String targetDay) {
		    datePickerInput.click();

		    String existingMonthYear = Month_Year_Btn.getText();

		    if (existingMonthYear.contains(targetMonth) && existingMonthYear.contains(targetYear)) {
		        clickOnTargetDay(targetDay);
		    } else {
		        Month_Year_Btn.click();

		        if (Year_Btn.getText().equals(targetYear)) {
		            clickOnTargetMonth(targetMonth);
		        } else {
		            Year_Btn.click();
		            clickOnTargetYear(targetYear);
		            clickOnTargetMonth(targetMonth);
		        }
		        clickOnTargetDay(targetDay);
		    }
		}
		private void clickOnTargetDay(String targetDay) {
		    for (WebElement date : Dates) {
		        if (date.getText().equals(targetDay)) {
		            date.click();
		            break;
		        }
		    }
		}
		private void clickOnTargetMonth(String targetMonth) {
		    for (WebElement month : Months) {
		        if (month.getText().equals(targetMonth)) {
		            month.click();
		            break;
		        }
		    }
		}

		private void clickOnTargetYear(String targetYear) {
		    for (WebElement year : Years) {
		        if (year.getText().equals(targetYear)) {
		            year.click();
		            break;
		        }
		    }
		}
		//Confirmation popup Handling
		 public String ActualConfirmationMsg;
		 public String ExpectedConfirmationMsg="Are You Sure?";

		 public void DismissPopup() {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		    	Alert ConfirmationPopup=wait.until(ExpectedConditions.alertIsPresent());
		    	ActualConfirmationMsg=ConfirmationPopup.getText();
		    	ConfirmationPopup.dismiss();
		 }
		 public void AcceptPopup() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		    	Alert ConfirmationPopup=wait.until(ExpectedConditions.alertIsPresent());
		    	ConfirmationPopup.accept();
		    	Thread.sleep(2000);
		 }


}