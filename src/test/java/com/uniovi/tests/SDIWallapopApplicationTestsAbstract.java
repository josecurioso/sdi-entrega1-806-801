package com.uniovi.tests;

import com.uniovi.tests.pageobject.*;
import com.uniovi.utils.SeleniumUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class SDIWallapopApplicationTestsAbstract {

    // Luis
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "C:\\Users\\Pedro\\Desktop\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
    // automáticas)):

    // Jose portatil
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "D:\\Escritorio\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

    //Jose
    static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckdriver024 = "D:\\Escritorio\\geckodriver024win64.exe";



    static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
    static String URL; //No se inicializa para dejar que la implementacion local o remota decida la url


    public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckdriver);
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    // Antes de cada prueba se navega al URL home de la aplicaciónn
    @Before
    public void setUp() {
        init();
        driver.navigate().to(URL);
    }

    // Después de cada prueba se borran las cookies del navegador
    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    abstract void init();

    // Antes de la primera prueba
    @BeforeClass
    static public void begin() {
    }

    // Al finalizar la última prueba
    @AfterClass
    static public void end() {
        // Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    public void PR01() {
        PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_RegisterView.fillForm(driver, "UO257809@uniovi.es", "Luis", "pastrana", "77777",
        "77777");
        PO_View.checkElement(driver, "text", "Bienvenido a WallapopSDI");
    }

    @Test
    public void PR02() {
        PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_RegisterView.fillForm(driver, "", "", "", "77777",
        "77777");
        PO_View.checkElement(driver, "text", "Registrate");
    }

    @Test
    public void PR03() {
        PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_RegisterView.fillForm(driver, "UO257809@uniovi.es", "Luis", "pastrana", "77777",
        "777773434");
        PO_View.checkElement(driver, "text", "Registrate");
    }

    @Test
    public void PR04() {
        PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_RegisterView.fillForm(driver, "marta@gmail.com", "marta", "pastrana", "77777",
        "77777");
        PO_View.checkElement(driver, "text", "Registrate");
    }

    @Test
    public void PR05() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "admin@gmail.com" , "admin" );
        PO_NavView.checkElement(driver, "id", "users-menu");
    }

    @Test
    public void PR06() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com" , "123456" );
        PO_View.checkElement(driver, "id", "addOffer");
    }

    @Test
    public void PR07() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "a" , "a" );
        PO_LoginView.checkKey(driver, "Error.login.incorrect",
        PO_Properties.getSPANISH() );
    }

    @Test
    public void PR08() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com" , "noesesta" );
        PO_LoginView.checkKey(driver, "Error.login.incorrect",
        PO_Properties.getSPANISH() );
    }

    @Test
    public void PR09() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "no@existe.com" , "noexiste" );
        PO_LoginView.checkKey(driver, "Error.login.incorrect",
        PO_Properties.getSPANISH() );
    }

    @Test
    public void PR10() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com" , "123456" );
        PO_View.checkElement(driver, "id", "addOffer");
        PO_NavView.clickOption(driver, "logout", "class", "btn btn-primary");
        PO_View.checkElement(driver, "text", "Identificate");
    }

    @Test
    public void PR11() {
        (new WebDriverWait(driver, PO_View.getTimeout())).until(ExpectedConditions.invisibilityOfElementLocated(By.id("logout")));
    }

    @Test
    public void PR12() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "admin@gmail.com" , "admin" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
        elementos.get(0).click();
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
        elementos.get(0).click();

        List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free",
        "//tbody/tr", PO_View.getTimeout());
        assertEquals(5 , elementos2.size());
    }

    @Test
    public void PR13() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
        elementos.get(0).click();
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
        elementos.get(0).click();

        List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        assertEquals(5, usersList.size());// igual el num esta mal

        usersList.get(0).findElement(By.id("idsUsers")).click();

        List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
        listPage.get(0).click();
        usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        assertEquals(4, usersList.size());// esto seria 1 menos
    }

    @Test
    public void PR14() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
        elementos.get(0).click();
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
        elementos.get(0).click();

        List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        assertEquals(5, usersList.size());// igual el num esta mal

        usersList.get(usersList.size() - 1).findElement(By.id("idsUsers")).click();

        List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
        listPage.get(0).click();
        usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        assertEquals(4, usersList.size());// igual el num esta mal
    }

    @Test
    public void PR15() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
        //probar PO_View clickOption
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
        elementos.get(0).click();
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
        elementos.get(0).click();

        List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        assertEquals(5, usersList.size());// igual el num esta mal

        usersList.get(usersList.size() - 1).findElement(By.id("idsUsers")).click();
        usersList.get(usersList.size() - 2).findElement(By.id("idsUsers")).click();
        usersList.get(usersList.size() - 3).findElement(By.id("idsUsers")).click();

        List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
        listPage.get(0).click();
        usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        assertEquals(2, usersList.size());// esto seria  3 menos
    }

    @Test
    public void PR16() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'addOffer')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Agregar Oferta");
        PO_PrivateView.fillFormAddOffer(driver, "Prueba", "Descripción de prueba", "150");

        PO_View.checkElement(driver, "text", "Mis ofertas");
        PO_View.checkElement(driver, "text", "Prueba");
    }

    @Test
    public void PR17() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'addOffer')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Agregar Oferta");
        PO_PrivateView.fillFormAddOffer(driver, "", "Descripción de prueba", "150");
        PO_View.checkElement(driver, "text", "Este campo no puede ser vacío");
    }

    @Test
    public void PR18() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listMyOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Mis ofertas");

        PO_View.checkElement(driver, "text", "Nintendo");
        PO_View.checkElement(driver, "text", "Portátil");
    }

    @Test
    public void PR19() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listMyOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Mis ofertas");
        elementos = PO_View.checkElement(driver, "text", "Eliminar");
        int temp = elementos.size();
        elementos.get(0).click(); //Borramos el primero
        (new WebDriverWait(driver, PO_View.getTimeout())).until(ExpectedConditions.numberOfElementsToBe(By.className("btn"), 3));
    }

    @Test
    public void PR20() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listMyOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Mis ofertas");
        elementos = PO_View.checkElement(driver, "text", "Eliminar");
        int temp = elementos.size();
        elementos.get(elementos.size()-1).click(); //Borramos el ultimo
        (new WebDriverWait(driver, PO_View.getTimeout())).until(ExpectedConditions.numberOfElementsToBe(By.className("btn"), 3));
    }

    @Test
    public void PR21() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Ofertas");
        PO_PrivateView.fillFormSearchBox(driver, "");
        elementos = PO_View.checkElement(driver, "free", "//tr");
        System.out.println(elementos.size());
        assertEquals(5, elementos.size()-1);
    }

    @Test
    public void PR22() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Ofertas");
        PO_PrivateView.fillFormSearchBox(driver, "supercalifragilisticoespialidoso");
        (new WebDriverWait(driver, PO_View.getTimeout())).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td")));
    }

    @Test
    public void PR23() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Ofertas");
        PO_PrivateView.fillFormSearchBox(driver, "Hitchikers guide to the galaxy");
        elementos = PO_View.checkElement(driver, "text", "Comprar");
        elementos.get(0).click();

        double moneyAfter = PO_OfferListView.getUserMoney(driver);
        assertTrue(moneyAfter > 0);
    }

    @Test
    public void PR24() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Ofertas");
        PO_PrivateView.fillFormSearchBox(driver, "Nintendo");
        elementos = PO_View.checkElement(driver, "text", "Comprar");
        elementos.get(0).click();

        double moneyAfter = PO_OfferListView.getUserMoney(driver);
        assertEquals(0, moneyAfter,0.1);
    }

    @Test
    public void PR25() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Ofertas");
        PO_PrivateView.fillFormSearchBox(driver, "Cristiano Ronaldo");
        elementos = PO_View.checkElement(driver, "text", "Comprar");
        double moneyBefore = PO_OfferListView.getUserMoney(driver);
        elementos.get(0).click();

        double moneyAfter = PO_OfferListView.getUserMoney(driver);
        assertEquals(moneyBefore, moneyAfter,0.1);

        PO_OfferListView.checkElement(driver, "text", "No puedes comprarlo, falta dinero"); //cambiar esto a id mejor
    }

    @Test
    public void PR26() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
        List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listMyBuys')]/a");
        elementos.get(0).click();

        PO_View.checkElement(driver, "text", "Pixel XL");
        PO_View.checkElement(driver, "text", "Joystick");
    }

    @Test
    public void PR27() {
        //Pag. Principal
        PO_NavView.changeIdiom(driver, "Spanish");
        PO_NavView.checkKey(driver, "welcome.message",PO_Properties.getSPANISH() );
        PO_NavView.checkKey(driver, "signup.message",PO_Properties.getSPANISH() );
        PO_NavView.changeIdiom(driver, "English");
        PO_NavView.checkKey(driver, "welcome.message",PO_Properties.getENGLISH() );
        PO_NavView.checkKey(driver, "signup.message",PO_Properties.getENGLISH() );

        //opciones user
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");

        PO_NavView.changeIdiom(driver, "Spanish");
        PO_NavView.checkKey(driver, "list.offers.user",PO_Properties.getSPANISH() );
        PO_NavView.checkKey(driver, "list.buys.user",PO_Properties.getSPANISH() );

        PO_NavView.changeIdiom(driver, "English");
        PO_NavView.checkKey(driver, "list.offers.user",PO_Properties.getENGLISH() );
        PO_NavView.checkKey(driver, "list.buys.user",PO_Properties.getENGLISH() );

        PO_NavView.clickOption(driver, "user/offer/add", "class", "btn btn-primary");
        //ADD OFFER

        PO_NavView.changeIdiom(driver, "Spanish");
        PO_NavView.checkKey(driver, "add.offers",PO_Properties.getSPANISH() );
        PO_NavView.checkKey(driver, "name",PO_Properties.getSPANISH() );

        PO_NavView.changeIdiom(driver, "English");
        PO_NavView.checkKey(driver, "add.offers",PO_Properties.getENGLISH() );
        PO_NavView.checkKey(driver, "name",PO_Properties.getENGLISH() );


        PO_NavView.clickOption(driver, "logout", "class", "btn btn-primary");

        //admin list user
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");

        PO_NavView.changeIdiom(driver, "Spanish");
        PO_NavView.checkKey(driver, "list.users",PO_Properties.getSPANISH() );
        PO_NavView.changeIdiom(driver, "English");
        PO_NavView.checkKey(driver, "list.users",PO_Properties.getENGLISH() );

        PO_NavView.clickOption(driver, "/user/list", "id", "btnDelete");

        PO_NavView.changeIdiom(driver, "Spanish");
        PO_NavView.checkKey(driver, "users.system.registered",PO_Properties.getSPANISH() );
        PO_NavView.checkKey(driver, "users",PO_Properties.getSPANISH() );
        PO_NavView.changeIdiom(driver, "English");
        PO_NavView.checkKey(driver, "users.system.registered",PO_Properties.getENGLISH() );
        PO_NavView.checkKey(driver, "users",PO_Properties.getENGLISH() );
    }

    @Test
    public void PR28() {
        driver.navigate().to(URL + "/user/list");
        PO_LoginView.checkKey(driver, "login.message", PO_Properties.getSPANISH());
    }

    @Test
    public void PR29() {
        driver.navigate().to(URL + "/user/offer/list");
        PO_LoginView.checkKey(driver, "login.message", PO_Properties.getSPANISH());
    }

    @Test
    public void PR30() {
        PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
        driver.navigate().to(URL + "/user/list");
        (new WebDriverWait(driver, PO_View.getTimeout())).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Usuarios')]")));
    }
}
