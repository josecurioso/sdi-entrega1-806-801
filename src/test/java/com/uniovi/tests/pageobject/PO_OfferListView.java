package com.uniovi.tests.pageobject;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_OfferListView extends PO_View {

    public static void buyItem(WebDriver driver, int id) {
        List<WebElement> buttonList = SeleniumUtils.EsperaCargaPagina(driver, "id", "buyButton"+id,
                PO_View.getTimeout());
        buttonList.get(0).click();

    }

    public static double getUserMoney(WebDriver driver) {
        List<WebElement> money = SeleniumUtils.EsperaCargaPagina(driver, "free", "//*[contains(text(),'Saldo')]//span",
                PO_View.getTimeout());

        return Double.parseDouble(money.get(0).getText());
    }

}
