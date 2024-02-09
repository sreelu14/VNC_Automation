package com.eduwis.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eduwis.base.Base_class;

public class TS_002_TC_AT_SA_0002 extends Base_class {
	
	    @Test(priority =1)
		public void MandatoryfieldValidation() {
			clp.Set_Admin_User_Details();
			dp.ClickOnAttendance_Module();
		    dp.ClickOnApproveLeave_Submodule();
			al.Click_Search();
		     boolean actual= al.ErrormsgValidation();
			 Assert.assertTrue(actual, "msg not valid");
			
		}
 
		  @Test(priority =2 )
		  public void ClassDropdownValidation(){
			  
			  clp.Set_Admin_User_Details();
			  dp.ClickOnAcademics_Module();
			  dp.ClickOnClass_Submodule();
			  ac.ExpectedCount();
			  dp.ClickOnAttendance_Module();
			  dp.ClickOnApproveLeave_Submodule();
			  al.ClickOn_ClassDropdown();
			  al.Select_Class();
			  Assert.assertEquals(ac.newCount, al.ActualCount,"the counts are not equal");	
		  }
		  
		  @Test(priority=3,dependsOnMethods = "ClassDropdownValidation")
		  public void SectionDropdownValidation() {
			
			  dp.ClickOnAcademics_Module();
			  dp.ClickOnClass_Submodule();
			  int seccount= ac.getSection("Grade-I");
			  dp.ClickOnAttendance_Module();
			  dp.ClickOnApproveLeave_Submodule();
			  al.Select_Class();
		      int secsize= al.Select_Section("Genius");
		      System.out.println(seccount);
		      System.out.println(secsize);
		      Assert.assertEquals(seccount, secsize,"section options are not being fetched correctly");
		      
		  }
		  @Test(priority=4,dependsOnMethods = { "ClassDropdownValidation","SectionDropdownValidation"})
		  public void SearchButtonValidation() {
			  
			  al.Click_Search();
			  boolean search_result=al.validate_Search();
			  Assert.assertEquals(search_result,true,"search results not displayed");
			  
			  
		  }
		  @Test(dependsOnMethods="SearchButtonValidation")
		  public void ClickAddButton() {
//			    clp.Set_Admin_User_Details();
//				dp.ClickOnAttendance_Module();
//			    dp.ClickOnApproveLeave_Submodule();
			 boolean clickadd = al.ValidateClickAdd();
			 Assert.assertTrue(clickadd, "AddLeave modal is not displayed");
		  }
		  

}
