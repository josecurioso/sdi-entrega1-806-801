package com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobject.*;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uniovi.utils.SeleniumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SDIWallapopApplicationTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):



	// Luis
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "C:\\Users\\Pedro\\Desktop\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// automáticas)):

	// Jose
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "D:\\Escritorio\\geckodriver024win64.exe";

    // Jose portatil
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "D:\\Escritorio\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";


	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";


    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OffersRepository offersRepository;


	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
        initDb();
		driver.navigate().to(URL);
	}


    public void initDb() {
		usersRepository.deleteAll();
		offersRepository.deleteAll();

		User user1 = new User("admin@gmail.com", "admin", "istrador");
		user1.setPassword("admin");
		user1.setRole(rolesService.getRoles()[2]);
		User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		User user3 = new User("maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[1]);
		User user4 = new User("marta@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		User user5 = new User("pela@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("ed@gmail.com", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[1]);


		Set user2Offers = new HashSet<Offer>() {
			{
				add(new Offer("Nintendo", "consola", 100.0, user2));
				add(new Offer("Portátil", "ordenador", 600.0, user2));
				add(new Offer("Millenium Falcon", "Lego Star Wars", 100.0, user2));
				add(new Offer("Seagate Barracuda 4Tb", "HDD", 100.0, user2));
			}
		};
		user2.setOffers(user2Offers);
		Set user3Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oneplus 5T", "movil", 400.0, user3));
				add(new Offer("Cristiano Ronaldo", "sabe jugar al futbol", 100000000.0, user3));
				add(new Offer("Pixel XL", "Mobile phone made by Google", 100.0, user3));
				add(new Offer("Joystick", "Thrustmaster", 100.0, user3));
			}
		};
		user3.setOffers(user3Offers);
		Set user4Offers = new HashSet<Offer>() {
			{
				add(new Offer("Hitchikers guide to the galaxy", "Douglas Adams book", 20.0, user4));
				add(new Offer("Clean Code", "Robert C. Martin book", 40.0, user4));
				add(new Offer("Maigol", "No darle de comer después de media noche", 100.0, user4));
				add(new Offer("Rubik's Cube", "El buen rompecabezas", 100.0, user4));
			}
		};
		user4.setOffers(user4Offers);
		Set user5Offers = new HashSet<Offer>() {
			{
				add(new Offer("Tesla Model  S", "electric SUV", 90000.0, user5));
				add(new Offer("Solid chat application", "fully decentralized as timbl likes it", 123.0, user5));
				add(new Offer("Hunger Games", "Some good literature", 100.0, user5));
				add(new Offer("BB8 Droid", "A helping ball", 100.0, user5));
			}
		};
		user5.setOffers(user5Offers);
		Set user6Offers = new HashSet<Offer>() {
			{
				add(new Offer("Starship", "Rocket stage capable of propulsive landing on Mars", 10.0, user6));
				add(new Offer("Super Heavy", "Booster suitable for the Starship second stage", 30.0, user6));
				add(new Offer("Josecurioso", "A este si que no le des de comer", 100.0, user6));
				add(new Offer("Network Switch", "Some fine equipment for the home", 100.0, user6));
			}
		};
		user6.setOffers(user6Offers);


		usersService.addUser(user2);
		usersService.addUser(user1);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

		User u2 = usersRepository.findByEmail("lucas@gmail.com");
		User u3 = usersRepository.findByEmail("maria@gmail.com");
		User u4 = usersRepository.findByEmail("marta@gmail.com");
		User u5 = usersRepository.findByEmail("pela@gmail.com");
		User u6 = usersRepository.findByEmail("ed@gmail.com");


		Offer o1 = offersRepository.searchByDescriptionNameAndUser("Millenium Falcon", u2).get(0);
		Offer o2 = offersRepository.searchByDescriptionNameAndUser("Seagate Barracuda 4Tb", u2).get(0);
		Offer o3 = offersRepository.searchByDescriptionNameAndUser("Pixel XL", u3).get(0);
		Offer o4 = offersRepository.searchByDescriptionNameAndUser("Joystick", u3).get(0);
		Offer o5 = offersRepository.searchByDescriptionNameAndUser("Maigol", u4).get(0);
		Offer o6 = offersRepository.searchByDescriptionNameAndUser("Rubik's Cube", u4).get(0);
		Offer o7 = offersRepository.searchByDescriptionNameAndUser("Hunger Games", u5).get(0);
		Offer o8 = offersRepository.searchByDescriptionNameAndUser("BB8 Droid", u5).get(0);
		Offer o9 = offersRepository.searchByDescriptionNameAndUser("Josecurioso", u6).get(0);
		Offer o10 = offersRepository.searchByDescriptionNameAndUser("Network Switch", u6).get(0);


		buy(o1, u3);
		buy(o2, u3);
		buy(o3, u4);
		buy(o4, u4);
		buy(o5, u5);
		buy(o6, u5);
		buy(o7, u6);
		buy(o8, u6);
		buy(o9, u2);
		buy(o10, u2);
	}

	public void buy(Offer offer, User user){
		offer.setSold(true);
		offer.setBuyer(user);
		offersRepository.save(offer);
		user.addBuy(offer);
		usersRepository.save(user);
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
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("logout")));

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
		assertEquals(6 , elementos2.size());
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
		assertEquals(6, usersList.size());// igual el num esta mal

		usersList.get(0).findElement(By.id("idsUsers")).click();

		List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
		listPage.get(0).click();
		usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(5, usersList.size());// esto seria 1 menos

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
		assertEquals(6, usersList.size());// igual el num esta mal

		usersList.get(usersList.size() - 1).findElement(By.id("idsUsers")).click();

		List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
		listPage.get(0).click();
		usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(5, usersList.size());// igual el num esta mal

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
		assertEquals(6, usersList.size());// igual el num esta mal

		usersList.get(usersList.size() - 1).findElement(By.id("idsUsers")).click();
		usersList.get(usersList.size() - 2).findElement(By.id("idsUsers")).click();
		usersList.get(usersList.size() - 3).findElement(By.id("idsUsers")).click();

		List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'btnDelete')]");
		listPage.get(0).click();
		usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(3, usersList.size());// esto seria  3 menos

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
        PO_View.checkElement(driver, "text", "Mis ofertas");
        elementos = PO_View.checkElement(driver, "text", "Eliminar");
		assertEquals(temp-1, elementos.size());
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
        PO_View.checkElement(driver, "text", "Mis ofertas");
        elementos = PO_View.checkElement(driver, "text", "Eliminar");
		assertEquals(temp-1, elementos.size());
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
		assertTrue(elementos.size()-1 == 5);
	}
	@Test
	public void PR22() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Ofertas");
		PO_PrivateView.fillFormSearchBox(driver, "supercalifragilisticoespialidoso");
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td")));
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

		//PO_OfferListView.checkElement(driver, "text", "Saldo no suficiente"); //cambiar esto a id mejor

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
		//Falta check del mensaje
	}

}