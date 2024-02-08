package com.eduwis.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Common_Login_Page extends Base_page {

	public Common_Login_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "username")
	public WebElement user_name;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement user_password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement Signin_Button;

	// class dropdown
	@FindBy(xpath = "//select[@id='class_id']")
	private WebElement class_options;
	// section dropdown
	@FindBy(xpath = "//select[@name='section_id']")
	WebElement SectionDropdown;

	//
	public void CLASS_DROPDOWN(String class_name) {

		Select sc = new Select(class_options);
		sc.selectByVisibleText(class_name);

	}

	public void SECTION_DROPDOWN(String section_name) {

		Select sc = new Select(SectionDropdown);
		sc.selectByVisibleText(section_name);

	}

	// Admin method
	public void Set_Admin_User_Details() {
		String u1 = rbs.getString("ADMIN_USERNAME");
		String p1 = rbs.getString("ADMIN_PASSWORD");

		try {
			user_name.sendKeys(u1);
			user_password.sendKeys(p1);
			Signin_Button.click();
			log.info("valid details ");

		} catch (Exception e) {
			log.info("invalid web elements ");

		}

	}

	public void Set_Superadmin_User_Details() {
		String u2 = rbs.getString("SUPERADMIN_USERNAME");
		String p2 = rbs.getString("SUPERADMIN_PASSWORD");
		try {
			driver.manage().deleteAllCookies();
			Thread.sleep(3000);
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(rbs.getString("COMMON_URL"));

			log.info("superadmin url launched ");
			user_name.sendKeys(u2);
			user_password.sendKeys(p2);
			Signin_Button.click();
			log.info("valid details ");
		} catch (Exception e) {
			log.info("invalid web elements ");

		}

	}

}
