package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		}
	
//		Meal Page:
//		get metode za sve potrebne elemente
//		metodu koja dodaje jelo u korpu - kao parametar se prosleđuje količina
//		metodu koja jelo dodaje u omiljena jela, klikom na dugme Favorite 

	public WebElement getQty () {
		return driver.findElement(By.name("product_qty"));
	}
	
	public WebElement getAddExtraToppingsBtn () {
		return driver.findElement(By.xpath("//div[@class='addons-wrapper js-addons-listing']"));
	}
	
	public WebElement getAddToCartBtn () {
		return driver.findElement(By.xpath("//div[@class=\"product-description\"]/div[3]/div[2]/a"));
	}
	
	public WebElement getAddToFavoriteBtn () {
		return driver.findElement(By.xpath("//*[@id=\"item_119\"]"));
	}
	
	public void addToCart (String quantity) {
		this.getQty().sendKeys(Keys.CONTROL + "a");
		this.getQty().sendKeys(quantity);
		this.getAddToCartBtn().click();
	}
	
	public void addToFavorite () {
		this.getAddToFavoriteBtn().click();
	}
	
	
	

}
