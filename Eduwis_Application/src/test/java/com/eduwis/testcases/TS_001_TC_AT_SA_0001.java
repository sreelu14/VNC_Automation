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
	  sa.Select_Class();
	  	  Assert.assertEquals(ac.newCount, sa.ActualCount,"the counts are not equal");	
  }
  @Test(dependsOnMethods = "ClassDropdownValidation")
  public void SectionDropdownValidation() {
	
	  dp.ClickOnAcademics_Module();
	  dp.ClickOnClass_Submodule();
	  ac.getSection("Grade-I");
	  dp.ClickOnAttendance_Module();
	  dp.ClickOnStudentAttendance_Submodule();
	  sa.Select_Class();
      sa.Select_Section("Genius");
  }
  @Test(dependsOnMethods="SectionDropdownValidation")
  public void StudentAttendance_Searching() throws InterruptedException {
	  sa.selectYearAndMonth("2023", "Jun", "2");
	  sa.Click_Search();
      sa.StudentListTable("uyy98");
      sa.Click_SaveAttendanceBtn();
      Assert.assertEquals(true, sa.MsgValidation(),"Succes msg not displayed");
      Thread.sleep(2000);
      }
}
