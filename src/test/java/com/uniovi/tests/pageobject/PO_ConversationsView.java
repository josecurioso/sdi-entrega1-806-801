package com.uniovi.tests.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ConversationsView extends PO_View{

	
	public static void clickOnMsg(WebDriver driver,long id) {
		List<WebElement> elementos = PO_View.checkElement(driver, "href", "msgButton"+id);
		elementos.get(0).click();
	}
}
