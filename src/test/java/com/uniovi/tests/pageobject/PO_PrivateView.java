package com.uniovi.tests.pageobject;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddOffer(WebDriver driver, String namep, String desriptionp, String pricep, boolean highlightp) {

		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);

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

		WebElement highlight = driver.findElement(By.name("highlighted"));
		if(highlightp)
			highlight.click();

		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	static public void fillFormSearchBox(WebDriver driver, String contentp) {

		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);

		// Rellenemos el campo de descripción
		WebElement searchText = driver.findElement(By.name("searchText"));
		searchText.clear();
		searchText.sendKeys(contentp);

		By boton = By.id("searchBtn");
		driver.findElement(boton).click();
	}
}