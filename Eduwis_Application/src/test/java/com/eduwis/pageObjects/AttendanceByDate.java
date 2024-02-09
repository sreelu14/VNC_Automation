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

public class AttendanceByDate extends Base_page {

	public AttendanceByDate(WebDriver driver) {
		super (driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

	@FindBy(xpath="//select[@id='class_id']")private WebElement Class_Dropdown;
	@FindBy(xpath="//select[@id='class_id']/option")private List<WebElement> classlist;
	@FindBy(name="date")private WebElement Date_Picker;
	@FindBy(xpath="//button[@value='search']")private WebElement Search_Btn;
	@FindBy(xpath="//table")public WebElement results;
	@FindBy(xpath="//select[@name='section_id']")private WebElement SectionDropdown;
    @FindBy(xpath="//tbody/tr")private List<WebElement>rows;
    @FindBy(xpath="//tbody/tr/td")private List<WebElement>columns;

    
    public void StudentListTable(String student) {
		int Rcount = rows.size();
		//System.out.println(Rcount);
		//int Ccount = columns.size();
		//System.out.println(Ccount);
		for (int r = 1; r <= Rcount; r++) {
			for (int c = 2; c <= 4; c++) {
				String data = driver
						.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+r+"]/td["+c+"]"))
						.getText();
				if(data.equals(student)) {
					
				 }
			}
		}
	}
	
	
	public int ActualCount;
	public void ClickOn_ClassDropdown() {
	    wait.until(ExpectedConditions.elementToBeClickable(Class_Dropdown)).click();
	    log.info("Clicked on Class_Dropdown");
	    ActualCount = classlist.size();
	   // System.out.println("no.of options in class dropdown is "+ActualCount);
	}

	public void Select_Class(String TargetClass) {
	    for (WebElement classOption : classlist) {
	        if (classOption.getText().equals(TargetClass)) {
	            classOption.click();
	            break;
	        }
	    }
	}
	
	public int ActSectionCount;
	public void Select_Section(String section) {
	    wait.until(ExpectedConditions.elementToBeClickable(SectionDropdown)).click();
		Select sectionlist= new Select(SectionDropdown);
		 ActSectionCount=sectionlist.getOptions().size()-1;
		//System.out.println("Sectionsize"+ActSectionCount);
		sectionlist.selectByVisibleText(section);
	}
    
}
