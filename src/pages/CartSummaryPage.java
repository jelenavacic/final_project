package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage {

	public CartSummaryPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		}

	
//		Cart Summary Page:
//		get metodu za Clear All dugme
//		metodu koja briše sve stavke iz korpe klikom na Clear All dugme
	
	public WebElement getClearAllBtn () {
		return driver.findElement(By.xpath("//*[@id=\"cartSummary\"]/div/div[1]/a[2]"));
	}
	
	public void clearAll () {
		this.getClearAllBtn().click();
	}

}
