package com.uniovi.tests;

import com.uniovi.tests.pageobject.PO_LoginView;
import com.uniovi.tests.pageobject.PO_NavView;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SDIWallapopApplicationTestsRemote extends SDIWallapopApplicationTestsAbstract {

	@Override
	void init() {
		URL = "http://ec2-3-95-230-31.compute-1.amazonaws.com:8080";
		driver.navigate().to(URL);
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		driver.navigate().to(URL + "/regenerate");
		driver.manage().deleteAllCookies();
	}

}