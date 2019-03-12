package com.uniovi.tests.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {

	public static void fillForm(WebDriver driver, String email, String passwd) {
		WebElement username = driver.findElement(By.name("username"));
		username.click();
		username.clear();
		username.sendKeys(email);	
		
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwd);	
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}
