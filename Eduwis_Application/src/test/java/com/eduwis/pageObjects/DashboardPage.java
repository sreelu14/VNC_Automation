package com.eduwis.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends Base_page {
	public DashboardPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[contains(text(),'Attendance')]")private WebElement Attendance_Module;
	@FindBy(xpath="(//a[contains(text(),'Student Attendance')])[2]")private WebElement StudentAttendance_Submodule;
	@FindBy(xpath="(//a[contains(text(),'Approve Leave')])[3]")private WebElement ApproveLeave_Submodule;
	@FindBy(xpath="//span[contains(text(),'Academics')]")private WebElement Academics_Module;
	@FindBy(xpath="(//a[contains(text(),'Class')])[6]")private WebElement Class_Submodule;
	@FindBy(xpath="(//a[contains(text(),'Attendance By Date')])[3]")private WebElement AttendenceByDate_SubModule;
	
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	public void ClickOnAttendance_Module() {
	    wait.until(ExpectedConditions.elementToBeClickable(Attendance_Module)).click();
	}
	public void ClickOnStudentAttendance_Submodule() {
	    wait.until(ExpectedConditions.elementToBeClickable(StudentAttendance_Submodule)).click();
	}
	public void ClickOnApproveLeave_Submodule() {
	    wait.until(ExpectedConditions.elementToBeClickable(ApproveLeave_Submodule)).click();
	}
	public void ClickOnAcademics_Module() {
	    wait.until(ExpectedConditions.elementToBeClickable(Academics_Module)).click();
	}
	public void ClickOnClass_Submodule() {
	    wait.until(ExpectedConditions.elementToBeClickable(Class_Submodule)).click();
	}
	public void ClickOnAttendanceByDate_Submodule() {
	    wait.until(ExpectedConditions.elementToBeClickable(AttendenceByDate_SubModule)).click();
	}
}