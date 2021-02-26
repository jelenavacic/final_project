package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		}
	
//		Bonus: Search Result Page:
//		get metodu za sve rezultate pretrage //*[@class='product-name']/a
//		metodu koja vraća nazive svih jela dobijenih pretragom
//		metodu koja vraća broj rezultata pretrage

	public List<WebElement> allSearchResults () {
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}
	
	public ArrayList<String> mealName () {
		ArrayList<String> names = new ArrayList <String>();
		for (int i = 0; i < this.allSearchResults().size(); i++) {
			String meal = this.allSearchResults().get(i).getText();
			names.add(meal);
			}
		return names;
	}
	
	public int numberOfResults () {
		return this.allSearchResults().size();
	}
	

}
