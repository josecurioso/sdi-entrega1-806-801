package com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.tests.pageobject.PO_LoginView;
import com.uniovi.tests.pageobject.PO_NavView;
import com.uniovi.tests.pageobject.PO_OfferListView;
import com.uniovi.tests.pageobject.PO_Properties;
import com.uniovi.tests.pageobject.PO_RegisterView;
import com.uniovi.tests.pageobject.PO_View;
import com.uniovi.utils.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SDIWallapopApplicationTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):

	// Luis
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Pedro\\Desktop\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// automáticas)):

	// Jose
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "D:\\Escritorio\\geckodriver024win64.exe";

	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {

		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

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
		PO_RegisterView.fillForm(driver, "UO257809@uniovi.es", "Luis", "pastrana", "77777", "77777");
		//PO_View.checkElement(driver, "text", "Bienvenido a WallapopSDI");
		PO_LoginView.checkKey(driver, "login.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR02() {
		PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "77777", "77777");
	//	PO_View.checkElement(driver, "text", "Registrate");
		PO_RegisterView.checkKey(driver, "signup.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR03() {
		PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "UO257809@uniovi.es", "Luis", "pastrana", "77777", "777773434");
		//PO_View.checkElement(driver, "text", "Registrate");
		PO_RegisterView.checkKey(driver, "signup.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR04() {
		PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "marta@gmail.com", "marta", "pastrana", "77777", "77777");
		//PO_View.checkElement(driver, "text", "Registrate");
		PO_RegisterView.checkKey(driver, "signup.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR05() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_NavView.checkElement(driver, "id", "users-menu");
	}

	@Test
	public void PR06() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		PO_View.checkElement(driver, "id", "offers-menu");
	}

	@Test
	public void PR07() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "a", "a");
		PO_LoginView.checkKey(driver, "Error.login.incorrect", PO_Properties.getSPANISH());
	}

	@Test
	public void PR08() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "noesesta");
		PO_LoginView.checkKey(driver, "Error.login.incorrect", PO_Properties.getSPANISH());
	}

	@Test
	public void PR09() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "no@existe.com", "noexiste");
		PO_LoginView.checkKey(driver, "Error.login.incorrect", PO_Properties.getSPANISH());
	}

	// logout
	@Test
	public void PR10() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		PO_View.checkElement(driver, "id", "offers-menu");
		PO_NavView.clickOption(driver, "logout", "class", "btn btn-primary");
		//PO_View.checkElement(driver, "text", "Identificate");
		PO_LoginView.checkKey(driver, "login.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR11() {
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("logout")));

	}

	@Test
	public void PR12() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();

		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(elementos2.size(), 7);
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
		assertTrue(usersList.size() == 6);// igual el num esta mal

		usersList.get(0).findElement(By.id("idsUsers")).click();

		List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
		listPage.get(0).click();
		assertTrue(usersList.size() == 5);// esto seria 1 menos

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
		assertTrue(usersList.size() == 6);// igual el num esta mal

		usersList.get(usersList.size()).findElement(By.id("idsUsers")).click();

		List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
		listPage.get(0).click();
		assertTrue(usersList.size() == 5);// esto seria 1 menos

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
		assertTrue(usersList.size() == 6);// igual el num esta mal

		usersList.get(usersList.size()).findElement(By.id("idsUsers")).click();
		usersList.get(usersList.size() - 1).findElement(By.id("idsUsers")).click();
		usersList.get(usersList.size() - 2).findElement(By.id("idsUsers")).click();

		List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
		listPage.get(0).click();
		assertTrue(usersList.size() == 5);// esto seria  3 menos

	}

	@Test
	public void PR23() {

		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		PO_NavView.clickOption(driver, "/offer/list", "id", "updateButton"); //reconoce el boton actualizar
		double moneyBefore=PO_OfferListView.getUserMoney(driver);
		PO_OfferListView.buyItem(driver,2);
		double moneyAfter=PO_OfferListView.getUserMoney(driver);
		assertEquals(moneyBefore, moneyAfter-20,0.1); //suponemos q cuesta 20 la oferta

	}
	@Test
	public void PR24() {

		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		PO_NavView.clickOption(driver, "offer/list", "id", "updateButton"); //reconoce el boton actualizar
		PO_OfferListView.buyItem(driver,2);
		double moneyAfter=PO_OfferListView.getUserMoney(driver);
		assertEquals(moneyAfter, 0,0.1); 

	}
	
	@Test
	public void PR25() {

		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		PO_NavView.clickOption(driver, "offer/list", "id", "updateButton"); //reconoce el boton actualizar
		double moneyBefore=PO_OfferListView.getUserMoney(driver);
		PO_OfferListView.buyItem(driver,2);
		double moneyAfter=PO_OfferListView.getUserMoney(driver);
		assertEquals(moneyBefore, moneyAfter,0.1);
		PO_OfferListView.checkElement(driver, "text", "Saldo no suficiente"); //cambiar esto a id mejor

	}
	
	@Test
	public void PR26() {

		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		PO_NavView.clickOption(driver, "user/offer/list", "id", "updateButton"); //reconoce el boton actualizar
		List<WebElement> offerList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(offerList.size() == 2); //igual falla el num de ofertas


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
	}


//	//PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.
//	//P13. Ver la lista de Notas.
//	@Test
//	public void PR13() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999990A" , "123456" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Notas del usuario");
//	SeleniumUtils.esperarSegundos(driver, 1);
//	//Contamos las notas
//	By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
//	driver.findElement(enlace).click();
//	SeleniumUtils.esperarSegundos(driver, 1);
//	//Esperamos por la ventana de detalle
//	PO_View.checkElement(driver, "text", "Detalles de la nota");
//	SeleniumUtils.esperarSegundos(driver, 1);
//	//Ahora nos desconectamos
//
//	PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//	
//	//P14. Loguearse como profesor y Agregar Nota A2.
//	//P14. Esta prueba podría encapsularse mejor ...
//	@Test
//	public void PR14() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999993D" , "123456" );
//	//COmprobamos que entramos en la pagina privada del Profesor
//	PO_View.checkElement(driver, "text", "99999993D");
//	//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
//	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'marks-menu')]/a");
//	elementos.get(0).click();
//	//Esperamos a aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
//	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
//	//Pinchamos en agregar Nota.
//	elementos.get(0).click();
//	//Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
//	PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");
//	//Esperamos a que se muestren los enlaces de paginación la lista de notas
//	elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//	//Nos vamos a la última página
//	elementos.get(3).click();
//	//Comprobamos que aparece la nota en la pagina
//	elementos = PO_View.checkElement(driver, "text", "Nota Nueva 1");
//	//Ahora nos desconectamos
//	PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//	//PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota Nueva 1.
//	//PRN. Ver la lista de Notas.
//	@Test
//	public void PR15() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999993D" , "123456" );
//	//COmprobamos que entramos en la pagina privada del Profesor
//	PO_View.checkElement(driver, "text", "99999993D");
//	//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
//	List<WebElement> elementos = PO_View.checkElement(driver, "free",
//	"//li[contains(@id, 'marks-menu')]/a");
//	elementos.get(0).click();
//	//Pinchamos en la opción de lista de notas.
//	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'mark/list')]");
//	elementos.get(0).click();
//	//Esperamos a que se muestren los enlaces de paginacion la lista de notas
//	elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//	//Nos vamos a la última página
//	elementos.get(3).click();
//	//Esperamos a que aparezca la Nueva nota en la ultima pagina
//	//Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
//	//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(text(),'mark/delete')]"
//	elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
//	elementos.get(0).click();
//	//Volvemos a la última pagina
//	elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//	elementos.get(3).click();
//	//Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
//	SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1",PO_View.getTimeout() );
//	//Ahora nos desconectamos
//	PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//}

}