package com.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.libraries.Base;

public class PhpTravels extends Base {
	String Url="https://phptravels.com/";
	private WebDriver driver;
	public PhpTravels(WebDriver _driver) {
		this.driver=_driver;
	}
	public void gotoHomepge() {
		driver.get(Url);
		WebElement signup=driver.findElement(By.cssSelector("li.jfHeader-menuListItem.hasSubMenu.signup-btn-wrapper > a"));
		myLibGS.highlightWebElement(signup);
		signup.click();
		
		String parent=driver.getWindowHandle();

		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);

		WebElement firstname=driver.findElement(By.cssSelector("#inputFirstName"));
		myLibGS.highlightWebElement(firstname);
		firstname.sendKeys("indra");
		WebElement lastname=driver.findElement(By.cssSelector("#inputLastName"));
		myLibGS.highlightWebElement(lastname);
		lastname.sendKeys("Bhusal");
		WebElement email=driver.findElement(By.cssSelector("#inputEmail"));
		myLibGS.highlightWebElement(lastname);
		email.sendKeys("ibhusal123@gmail.com");//
		
		WebElement phone=driver.findElement(By.cssSelector("#containerNewUserSignup > div:nth-child(1) > div > div > div:nth-child(4) > div > div > div"));
		myLibGS.highlightWebElement(phone);
		phone.click();
		WebElement element=driver.findElement(By.className("country-list"));
		List<WebElement>countryList=element.findElements(By.tagName("li"));
		for(WebElement countryName:countryList) {
			if(countryName.equals("United States")) {
				countryName.click();
				myLibGS.customWait(10);
			}
		}
		
		
		}

		}
	
	}

}
