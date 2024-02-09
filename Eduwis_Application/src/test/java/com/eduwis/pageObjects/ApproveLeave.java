package com.eduwis.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApproveLeave extends Base_page {

	public ApproveLeave(WebDriver driver) {
		super(driver);
	} 
	@FindBy(xpath="//select[@id='searchclassid']")private WebElement Class_Dropdown;
	@FindBy(xpath="//select[@id='searchclassid']/option")private List<WebElement> classlist;
	@FindBy(name="date")private WebElement Date_Picker;
	@FindBy(xpath="//button[@value='search_filter']")private WebElement Search_Btn;
	@FindBy(xpath="//select[@name='section_id']")private WebElement SectionDropdown;
    @FindBy(xpath="//tbody/tr")private List<WebElement>rows;
    @FindBy(xpath="//tbody/tr/td")private List<WebElement>columns;
    @FindBy(xpath="//button[@value='saveattendence']")private WebElement SaveAttendance;
    @FindBy(xpath="//div[@class='alert alert-success text-left']")public WebElement SuccessMessage;
    @FindBy(xpath="")private WebElement Calender;
    @FindBy(xpath="(//span[@class='class_id_error text-danger'])[1]")WebElement errormsg_class;
    @FindBy(xpath="(//span[@class='class_id_error text-danger'])[2]")WebElement errormsg_section;
    @FindBy(xpath="//tr[@class='odd']")WebElement table_rows;
    @FindBy(xpath="//button[@class='btn btn-sm btn-primary ']") WebElement Add_button;
    @FindBy(xpath="//h4[contains(text(),'Add Leave')]")WebElement AddLeave_Modal;
    
   
	
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
		int actualcount=noofOptions-1;
		sectionlist.selectByVisibleText(section);
		return actualcount;
	}
	public void Set_Date(String date) {
	    wait.until(ExpectedConditions.elementToBeClickable(Date_Picker)).click();
	    Date_Picker.sendKeys(date);
	}
	public void Click_Search() {
	    wait.until(ExpectedConditions.elementToBeClickable(Search_Btn)).click();
	}
	
	public boolean ErrormsgValidation() {
		String error_class="The Class field is required.";
		String error_section="The Section field is required.";
		String actualerror_class=errormsg_class.getText();
		String actualerror_section=errormsg_section.getText();
		boolean temp =false;
		
		
		if(errormsg_class.isDisplayed()&&errormsg_section.isDisplayed()) {
			if(error_class.equalsIgnoreCase(actualerror_class)&& error_section.equalsIgnoreCase(actualerror_section)) 
				
			temp=true;
			}
				
	//	System.out.println("errormsgvalidation"+temp);
		return temp;
	}
	
	public boolean validate_Search() {
	    boolean temp=false;
		String empty= "No data available in table ";
		if(table_rows != null) {
		if(table_rows.isDisplayed())
		{
			temp = true;
		}
			
	}
		return temp;
	}
		
   public boolean ValidateClickAdd() {
	  try {
		  Add_button.click();
	      wait.until(ExpectedConditions.visibilityOf(AddLeave_Modal));
	      return true;
	  }
	  catch(NoSuchElementException e) {
		   
		   return false;
	  }
	
		
	   
   }
	
}
