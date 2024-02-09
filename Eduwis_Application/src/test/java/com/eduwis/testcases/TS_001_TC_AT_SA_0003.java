package com.eduwis.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eduwis.base.Base_class;

public class TS_001_TC_AT_SA_0003 extends Base_class{
	
	@Test(priority = 1)
	public void ClassDropdownValidation() {
		   clp.Set_Admin_User_Details();
		dp.ClickOnAcademics_Module();
		dp.ClickOnClass_Submodule();
		ac.ExpectedCount();
	   dp.ClickOnAttendance_Module();
	   dp.ClickOnStudentAttendance_Submodule();
	   sa.ClickOn_ClassDropdown();
	   sa.Select_Class("Grade-I");
	   sa.Select_Section("Genius");
	   bp.selectDate("2023", "Dec", "2");
	   sa.Click_Search();
	   SAssert.assertTrue(sa.results.isDisplayed(), " 'No data available in table' is displayed ");
	   sa.StudentListTable("uyy98");
	   sa.Click_SaveAttendanceBtn();
	   dp.ClickOnAttendanceByDate_Submodule();
	   sa.ClickOn_ClassDropdown();
	   sa.Select_Class("Grade-I");
	   sa.Select_Section("Genius");
	   bp.selectDate("2023", "Dec", "2");
	   
	    

	}
}
