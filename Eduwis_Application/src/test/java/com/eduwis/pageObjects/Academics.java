package com.eduwis.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Academics extends Base_page {

	public Academics(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//div[contains(text(),'Records: 1 to 8 of 8')]")private WebElement ClassList_String;
	@FindBy(xpath=".//tr")
    List<WebElement> rows;
	@FindBy(xpath = ".//td[1]")
	 List<WebElement> firstColumnElements;
	@FindBy(tagName="div")
	List<WebElement> divElements ;
	int count=0;
	List<WebElement> secondColumnElements;
	
	public int newCount;
	public  void ExpectedCount() {
		String txt = ClassList_String.getText();
		int count=Integer.parseInt(txt.substring(txt.indexOf("f")+2));
	  newCount=count+1;
			System.out.println(newCount);
		log.info("Class Count extracted");
	}


public int getSection(String classname) {
		int size=rows.size()-1;
		for(int i=0;i<size;i++) {
			String name = firstColumnElements.get(i).getText();
			System.out.println("gradename "+name);
			if (name.equalsIgnoreCase(classname))
				{
				secondColumnElements = rows.get(i).findElements(By.xpath(".//td[2]/div"));
		        System.out.println("Sections in Class module : "+secondColumnElements.size());
		        break;
				}
		}
		return secondColumnElements.size();
}
}

