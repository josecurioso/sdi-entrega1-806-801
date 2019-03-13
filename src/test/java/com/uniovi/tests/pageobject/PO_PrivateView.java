package com.uniovi.tests.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddOffer(WebDriver driver, String namep, String desriptionp, String pricep) {

		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);

		// Seleccionamos el alumnos userOrder
		//new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);

		// Rellenemos el campo de descripción
		WebElement name = driver.findElement(By.name("name"));
		name.clear();
		name.sendKeys(namep);

		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(desriptionp);

		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(pricep);

		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}