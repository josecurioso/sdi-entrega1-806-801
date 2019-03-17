package com.uniovi.tests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_ConversationsView extends PO_View{


    public static void clickOnMsg(WebDriver driver) {
        List<WebElement> elementos = PO_View.checkElement(driver, "text", "Enviar mensaje");
        elementos.get(0).click();
    }
}