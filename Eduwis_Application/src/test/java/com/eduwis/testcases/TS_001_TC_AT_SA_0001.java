package com.eduwis.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eduwis.base.Base_class;

public class TS_001_TC_AT_SA_0001 extends Base_class {
	 
  @Test
  public void ClassDropdownValidation(){
	  

	  clp.Set_Admin_User_Details();
	  dp.ClickOnAcademics_Module();
	  dp.ClickOnClass_Submodule();
	  ac.ExpectedCount();
	  dp.ClickOnAttendance_Module();
	  dp.ClickOnStudentAttendance_Submodule();
	  sa.ClickOn_ClassDropdown();
	  sa.Select_Class("Grade-I");
	  Assert.assertEquals(ac.newCount, sa.ActualCount,"the Class counts are not equal");	
	  logs.info("Class Dropdown Validated Successfully");
  }
  
  @Test(dependsOnMethods = "ClassDropdownValidation")
  public void SectionDropdownValidation() throws InterruptedException {
	
	  dp.ClickOnAcademics_Module();
	  dp.ClickOnClass_Submodule();
	  ac.getSections("Grade-I");
	  dp.ClickOnAttendance_Module();
	  dp.ClickOnStudentAttendance_Submodule();
	  sa.Select_Class("Grade-I");
	  Thread.sleep(2000);
      sa.Select_Section("Genius");
      Assert.assertEquals(sa.ActSectionCount,ac.ExpSectionCount,"the section counts are not equal");
	  logs.info("Section Dropdown Validated Successfully");

  }
  
  @Test(dependsOnMethods="SectionDropdownValidation")
  public void StudentAttendance_Searching() throws InterruptedException {
	  bp.selectDate("2023","Dec", "2");
	  sa.Click_Search();
	  Assert.assertTrue(sa.results.isDisplayed()," 'No data available in table' is displayed ");
  }
  
  @Test (dependsOnMethods="StudentAttendance_Searching")
  public void AttendenceMarking() {
      sa.StudentListTable("uyy98");
      sa.Click_SaveAttendanceBtn();
      Assert.assertTrue(sa.MsgValidation(),"Success msg not displayed"); 
      }
  
  @Test (dependsOnMethods="StudentAttendance_Searching",priority=1)
  public void HolidayMarking() {
	  sa.Select_Class("Grade-I");
	  sa.Select_Section("Genius");
	  bp.selectDate("2024", "Jan", "1");
	  sa.Click_Search();
	  sa.ClickOnMarkHoliday();
	 // Assert.assertEquals(bp.ActualConfirmationMsg, bp.ExpectedConfirmationMsg,"No popup displayed");
	  bp.DismissPopup();
	  Assert.assertTrue(sa.MarkHolidayCheckbox.isDisplayed());
	  sa.ClickOnMarkHoliday();
	  bp.AcceptPopup();
	  //Assert.assertFalse(sa.MarkHolidayCheckbox.isDisplayed());
  }
  
  
}
