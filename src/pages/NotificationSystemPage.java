package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

//		Notification Sistem Page:
//		get metodu za element koji prikazuje poruku
//		//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]
//		metodu koja vraća poruku koja se nalazi u obaveštenju
//		metodu koja čeka da obaveštenje nestane
//		čeka se da element //*[contains(@class, 'system_message')]
//		za atribut style dobije vrednost  "display: none;"

	public WebElement getMessage() {
		return driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String messageText() {
		return this.getMessage().getText();
	}
	
	
	public void waiting () {
		waiter.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[contains(@class, 'system_message')]"), "display: none;"));	
	}

}
