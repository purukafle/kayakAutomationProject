package com.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.libraries.Base;
import com.test.PhpTravels;

public class PhptravelHome extends Base {
	@Test
	public void homepageTest() {
		PhpTravels travels=new PhpTravels(driver);
		travels.gotoHomepge();
	}
	

}
