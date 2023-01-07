package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.libraries.Base;

public class KayakLogin extends Base {
	String url="https://www.kayak.com/";
	private WebDriver driver;
	public KayakLogin(WebDriver _driver) {
		this.driver=_driver;
		
	}
	public KayakLogin loginKayak() {
		driver.get(url);
		
		WebElement login=driver.findElement(By.linkText("Sign in"));
		login.click();
		myLibGS.customWait(5);
		WebElement loginwithemail=driver.findElement(By.cssSelector("button > div.Iqt3-button-container > div"));
		Actions action=new Actions(driver);
		action.moveToElement(loginwithemail).click().build().perform();
		myLibGS.customWait(10);
		
		//flignt menu with round trip btn
		
		WebElement triptype=driver.findElement(By.className("wIIH-fake-select"));
		triptype.click();
		
		Select select=new Select(triptype);
		select.selectByIndex(0);
		myLibGS.customWait(5);
		
		return this;
		
		
	}

}
