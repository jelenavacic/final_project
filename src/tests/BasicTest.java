package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public abstract class BasicTest {
	
//		Basic Test:
//		apstraktna klasa koja sadrži sve zajedničke funkcionalnosti za sve test klase
//		od dodatnih atributa ima:
//		baseUrl 
//		imejl i lozinku demo korisnika customer@dummyid.com/12345678a
//		BeforeClass metoda koja konfiguriše Selenium drajver
//		AfterMethod metoda koja u slučaju pada testa kreira screenshot stranice 
//		i te slike čuva u okviru screenshots direktorijuma. Nevezano za ishod testa metoda uvek briše sve kolačiće.
//		AfterClass metoda koja zatvara sesiju drajvera
//		sve ostale test klase nasleđuju ovu klasu
//	Sve linkove koje koristite u testovima moraju da budu u formi baseUrl + ruta. 
//	Na primer link za login stranu bi izgledao baseUrl + "/guest-user/login-form"
//	Za prijavu koristite atribute email i password Basic Test klase



	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected LocationPopupPage lpp;
	protected ProfilePage pp;
	protected LoginPage lp;
	protected NotificationSystemPage nsp;
	protected String baseUrl;
	protected String email;
	protected String password;
	
	
	
	//BaseURL: http://demo.yo-meals.com/;
	//email: customer@dummyid.com
	//password: 12345678a

	@BeforeClass

	public void setup() {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 30);
		this.js = (JavascriptExecutor) driver;
		this.lpp = new LocationPopupPage(driver, waiter, js);
		this.pp = new ProfilePage(driver, waiter, js);
		this.lp = new LoginPage(driver, waiter, js);
		this.nsp = new NotificationSystemPage(driver, waiter, js);
		this.baseUrl = "http://demo.yo-meals.com/";
		this.email = "customer@dummyid.com";
		this.password = "12345678a";

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}
	
	@AfterClass

	public void finish () {
		driver.quit();
	}
	
	@AfterMethod
	
	
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) { 
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE); //takes screenshot and keeps it in memory
		
		FileHandler.copy(source, new File ("./screenshots/2021-23-2-20-20-01.png"));
		
		driver.manage().deleteAllCookies();
		
		}

}
	
}
