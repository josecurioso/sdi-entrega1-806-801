package com.uniovi.tests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_ChatView extends PO_View {

    public static void enterTextAndSend(WebDriver driver, String texto) {
        List<WebElement> elementos = PO_View.checkElement(driver, "id", "msgButton");
        elementos.get(0).click();

        List<WebElement> textArea = PO_View.checkElement(driver, "id", "textMessage");
        textArea.get(0).sendKeys(texto);
        List<WebElement> sendButton = PO_View.checkElement(driver, "id", "sendButton");
        sendButton.get(0).click();

    }

}