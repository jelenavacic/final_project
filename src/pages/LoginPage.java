package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		}
	
//		Login Page:
//		get metode za sve potrebne elemente
//		metodu koja prijavljuje korisnika na sistem - kao parametri se prosleđuju imejl i lozinka

	public WebElement getUsername () {
		return driver.findElement(By.name("username"));
	}
	
	public WebElement getPassword () {
		return driver.findElement(By.name("password"));
	}
	
	public WebElement getLoginBtn () {
		return driver.findElement(By.name("btn_submit"));
	}
	
	public void login (String username, String password) {
		this.getUsername().sendKeys(username);
		this.getPassword().sendKeys(password);
		this.getLoginBtn().click();
		
	}
	
	public String getMessage () {
		//html/body/div[2]
		return driver.findElement(By.xpath("/html/body/div[2]")).getText();
	}
	

}
