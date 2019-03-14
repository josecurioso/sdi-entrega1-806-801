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
            }
        };
        user2.setOffers(user2Offers);

        Set user3Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oneplus 5T", "movil", 400.0, user3));
                add(new Offer("Cristiano Ronaldo", "sabe jugar al futbol", 100000000.0, user3));
            }
        };
        user3.setOffers(user3Offers);

        Set user4Offers = new HashSet<Offer>() {
            {
                add(new Offer("Hitchikers guide to the galaxy", "Douglas Adams book", 20.0, user4));
                add(new Offer("Clean Code", "Robert C. Martin book", 40.0, user4));
            }
        };
        user4.setOffers(user4Offers);

        Set user5Offers = new HashSet<Offer>() {
            {
                add(new Offer("Tesla Model  S", "electric SUV", 90000.0, user5));
                add(new Offer("Solid chat application", "fully decentralized as timbl likes it", 123.0, user5));
            }
        };
        user5.setOffers(user5Offers);

        Set user6Offers = new HashSet<Offer>() {
            {
                add(new Offer("Starship", "Rocket stage capable of propulsive landing on Mars", 10.0, user6));
                add(new Offer("Super Heavy", "Booster suitable for the Starship second stage", 30.0, user6));
            }
        };
        user6.setOffers(user6Offers);

        usersService.addUser(user2);
        usersService.addUser(user1);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);
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

	//logout
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
		PO_LoginView.fillForm(driver, "admin@gmail.com" , "admin" );
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();

		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free",
		"//tbody/tr", PO_View.getTimeout());
		assertEquals(6 , elementos2.size());
		PO_View.checkElement(driver, "text", "lucas@gmail.com");
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/delete/1')]");
		elementos.get(0).click();
		//(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(@href, 'user/delete/1')]")));
	}
	@Test
	public void PR14() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com" , "admin" );
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "ed@gmail.com");
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/delete/6')]");
		elementos.get(0).click();
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(@href, 'user/delete/6')]")));
	}
	@Test
	public void PR15() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com" , "admin" );
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/delete/5')]");
		elementos.get(0).click();
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(@href, 'user/delete/5')]")));
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/delete/4')]");
		elementos.get(0).click();
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(@href, 'user/delete/4')]")));
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/delete/3')]");
		elementos.get(0).click();
		(new WebDriverWait(driver, 200)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(@href, 'user/delete/3')]")));
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
		assertTrue(elementos.size() == temp-1);
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
		assertTrue(elementos.size() == temp-1);
	}
	@Test
	public void PR21() {
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucas@gmail.com" , "123456" );
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'listOffers')]/a");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Ofertas");
		PO_PrivateView.fillFormSearchBox(driver, "");
		elementos = PO_View.checkElement(driver, "free", "//td");
		assertTrue(elementos.size() == 5);
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
		PO_View.checkElement(driver, "free", "//*[contains(text(),'Saldo')]//span[contains(text(),'80.0')]");
	}
//
//	//PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto, .... pagination  pagination-centered, Error.signup.dni.length
//	@Test
//	public void PR06() {
//	//Vamos al formulario de registro
//	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//	//Rellenamos el formulario.
//	PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777",
//	"77777");
//	PO_View.getP();
//	//COmprobamos el error de DNI repetido.
//	PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate",
//	PO_Properties.getSPANISH() );
//	//Rellenamos el formulario.
//	PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777",
//	"77777");
//	//COmprobamos el error de Nombre corto .
//
//	PO_RegisterView.checkKey(driver, "Error.signup.name.length",
//	PO_Properties.getSPANISH() );
//	//Rellenamos el formulario.
//	PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777",
//	"77777");
//	}
//
//	//PRN. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456
//	@Test
//	public void PR07() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999990A" , "123456" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Notas del usuario");
//	}
//	@Test
//	public void PR08() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999993D" , "123456" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Notas del usuario");
//	}
//	@Test
//	public void PR09() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999988F" , "123456" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Notas del usuario");
//	}
//	@Test
//	public void PR10() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999990A" , "12" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Identifícate");
//	}
//	@Test
//	public void PR11() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999990A" , "123456" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Notas del usuario");
//	PO_NavView.clickOption(driver, "logout", "class", "btn btn-primary");
//	PO_View.checkElement(driver, "text", "Identifícate");
//	}
//	@Test
//	public void PR12() {
//	//Vamos al formulario de logueo.
//	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//	//Rellenamos el formulario
//	PO_LoginView.fillForm(driver, "99999990A" , "123456" );
//	//COmprobamos que entramos en la pagina privada de Alumno
//	PO_View.checkElement(driver, "text", "Notas del usuario");
//	//Contamos el número de filas de notas
//	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//	"//tbody/tr", PO_View.getTimeout());
//	assertTrue(elementos.size() == 4);
//	//Ahora nos desconectamos
//	PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//
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