package com.test;

import org.testng.annotations.Test;

import com.libraries.Base;
import com.pages.KayakLogin;

public class KayakPageTests extends Base{
	@Test
	public void gotoKayakPage() {
		KayakLogin login=new KayakLogin(driver);
		login.loginKayak();
	}

}
