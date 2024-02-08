package com.eduwis.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentAttendance extends Base_page {

	public StudentAttendance(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//select[@id='class_id']")private WebElement Class_Dropdown;
	@FindBy(xpath="//select[@id='class_id']/option")private List<WebElement> classlist;
	@FindBy(name="date")private WebElement Date_Picker;
	@FindBy(xpath="//button[@value='search']")private WebElement Search_Btn;
	@FindBy(xpath="//select[@name='section_id']")private WebElement SectionDropdown;
    @FindBy(xpath="//tbody/tr")private List<WebElement>rows;
    @FindBy(xpath="//tbody/tr/td")private List<WebElement>columns;
    @FindBy(xpath="//button[@value='saveattendence']")private WebElement SaveAttendance;
    @FindBy(xpath="//div[@class='alert alert-success text-left']")public WebElement SuccessMessage;
    @FindBy(xpath="")private WebElement Calender;
    
    
	public void StudentListTable(String student) {
		int Rcount = rows.size();
		System.out.println(Rcount);
		int Ccount = columns.size();
		System.out.println(Ccount);
		for (int r = 1; r <= Rcount; r++) {
			for (int c = 2; c <= 4; c++) {
				String data = driver
						.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+r+"]/td["+c+"]"))
						.getText();
				if(data.equals(student)) {
					 driver.findElement(By.xpath("(//input[@value='7'])["+r+"]")).click();
					 break;
				 }
			}
		}
	}
	
	
	
	public int ActualCount;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public void ClickOn_ClassDropdown() {
	    wait.until(ExpectedConditions.elementToBeClickable(Class_Dropdown)).click();
	    log.info("Clicked on Class_Dropdown");
	    ActualCount = classlist.size();
	   // System.out.println("no.of options in class dropdown is "+ActualCount);
	}

	public void Select_Class() {
	    for (WebElement classOption : classlist) {
	        if (classOption.getText().equals("Grade-I")) {
	            classOption.click();
	            break;
	        }
	    }
	}
	public int Select_Section(String section) {
	    wait.until(ExpectedConditions.elementToBeClickable(SectionDropdown)).click();
		Select sectionlist= new Select(SectionDropdown);
		int noofOptions=sectionlist.getOptions().size();
		System.out.println("Sectionsize"+noofOptions);
		sectionlist.selectByVisibleText(section);
		return noofOptions;
	}
	public void Set_Date(String date) {
	    wait.until(ExpectedConditions.elementToBeClickable(Date_Picker)).click();
	    Date_Picker.sendKeys(date);
	}
	public void Click_Search() {
	    wait.until(ExpectedConditions.elementToBeClickable(Search_Btn)).click();
	}
	public void Click_SaveAttendanceBtn() {
	    wait.until(ExpectedConditions.elementToBeClickable(SaveAttendance)).click();
	}
	public boolean MsgValidation() {
		boolean value=SuccessMessage.isDisplayed();
		return value;
	}

	 @FindBy(id="date")private WebElement datePickerInput ;
	 @FindBy(xpath="(//th[@class='datepicker-switch'])[1]")private WebElement Month_Year_Btn;
	 @FindBy(xpath="//tr/td[@class='active day' or @class='day']")private List<WebElement> Dates;
	 @FindBy(xpath="(//th[@class='datepicker-switch'])[2]")private WebElement Year_Btn;
	 @FindBy(xpath="//span[@class='month' or @class='month active']")private List<WebElement> Months;
	 @FindBy(xpath="//span[contains(@class,'year')]")private List<WebElement> Years;

	   public  void selectYearAndMonth(String TargetYear,String TargetMonth, String TargetDay) throws InterruptedException {
	       datePickerInput.click();
	       String ExistingMonth_year= Month_Year_Btn.getText();
	       if(ExistingMonth_year.contains(TargetMonth)&& ExistingMonth_year.contains(TargetYear)) {
	    	   for(WebElement date:Dates) {
	    		   if(date.getText().equals(TargetDay)) {
	    			   date.click();
	    	    	   Thread.sleep(2000);
	    			   break;
	    		   }
	    	   }
	       }else {
	    	   Month_Year_Btn.click();
	    	   Thread.sleep(2000);
	    	   if(Year_Btn.getText().equals(TargetYear)) {
	    		   for(WebElement month:Months) {
	    			   if(month.getText().equals(TargetMonth)) {
	    				   month.click();
	    				   break;
	    			   }
	    		   }
	    	   }else {
	    		   Year_Btn.click();
		    	   Thread.sleep(2000);
	    		   for(WebElement Year:Years) {
	    			   if(Year.getText().equals(TargetYear)) {
	    				   Year.click();
	    		    	   Thread.sleep(2000);
	    				   break;
	    			   }
	    		   }
	    			   
	    		   
	    	   }
	       }
	       

	     
	   }
}
