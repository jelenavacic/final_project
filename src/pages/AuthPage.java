package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

//		Auth Page:
//		get metode za sve potrebne elemente sa stranice   
//		metodu koja odjavljuje korisnika sa sistema

	public AuthPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		}
	
	public WebElement getUserBtn() {
		return driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/a"));
	}
	
	public WebElement getMyAccountBtn () {
		return driver.findElement(By.linkText("My Account"));
	}

	public WebElement getLogoutBtn() {
		// *[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/div/ul/li[2]/a
		return driver.findElement(By.xpath("//div[@class=\"my-account-dropdown\"]/ul/li[2]/a"));
	}
	
	public void logout() throws InterruptedException {
		this.getUserBtn().click();
		this.getLogoutBtn().click();
		Thread.sleep(2000);
	}
	
//	public WebElement getUserBtn() {
//	//// li[@class="filled "]/a'
//	//// *[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/a
//	// *[@id="header"]/div[2]/div/div[2]/div[2]/ul/li/a
//	return driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/a"));
//}
//
//public WebElement getLogoutBtn() {
//	// *[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/div/ul/li[2]/a
//	return driver.findElement(By.xpath("//div[@class=\"my-account-dropdown\"]/ul/li[2]/a"));
//}
//
//public void logout() throws InterruptedException {
//	this.getUserBtn().click();
//	this.getLogoutBtn().click();
//	Thread.sleep(2000);
//}

}
