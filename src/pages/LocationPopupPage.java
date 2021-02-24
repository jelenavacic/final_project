package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {
	
//		Location Popup Page:
//		get metoda za element koji prikazuje lokaciju u hederu 
//		get metodu za close element
//		get metode potrebne za implementaciju metode u kojoj se postavlja lokacija
//		getKeyword()
//		//*[@id='locality_keyword']
//		getLocationItem(String locationName)
//		//li/a[contains(text(), '" + locationName + "')]/..
//		getLocationInput()
//		//*[@id='location_id']
//		getSubmit()
//		//*[@name='btn_submit']
//		metodu koja otvara iskačući dijalog
//		klikom na lokaciju iz hedera
//		metodu koja postavlja lokaciju - naziv lokacije (locationName) se prosleđuje kao parametar metode
//		metoda prvo klikne na element keyword element
//		čita vrednost data-value atributa location item elementa
//		postavlja lokaciju izvršavajući JavaScript kod
//		Skripta: arguments[0].value=arguments[1]
//		prvi argument skripte je location input
//		drugi argument skripte je vrednost pročitanog atributa iz drugog koraka.
//		Klikće na submit element preko skripte arguments[0].click();
//		metodu koja zatvara iskačući dijalog, klikom na X dugme


	public LocationPopupPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		}
	
//	get metoda za element koji prikazuje lokaciju u hederu 
	public WebElement getLocationInHeader () {
		return driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[1]/div/a/i"));
	}
	
//	get metodu za close element
	public WebElement getCloseElement () {
		return driver.findElement(By.xpath("//*[@id=\"location-popup\"]/div/div/div/div/a"));
	}
	
	
	public WebElement getKeyword () {
		return driver.findElement(By.id("locality_keyword"));	
	}
	
	public WebElement getLocationItem (String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
		
	public WebElement getLocationInput () {
		return driver.findElement(By.id("location_id"));
	}
	
	public WebElement getSubmit () {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

//	metodu koja otvara iskačući dijalog
//	klikom na lokaciju iz hedera
	
	public void popUpLocation () {
		this.getLocationInHeader().click();
	}
//	metodu koja postavlja lokaciju - naziv lokacije (locationName) se prosleđuje kao parametar metode
//	metoda prvo klikne na element keyword element
//	čita vrednost data-value atributa location item elementa
//	postavlja lokaciju izvršavajući JavaScript kod
//	Skripta: arguments[0].value=arguments[1]
//	prvi argument skripte je location input
//	drugi argument skripte je vrednost pročitanog atributa iz drugog koraka.
//	Klikće na submit element preko skripte arguments[0].click();

	
	public void setLocation (String locationName) throws InterruptedException {
		this.getLocationInHeader().click();
		this.getKeyword().click();
		String location = this.getLocationItem(locationName).getAttribute("data-value");
		Thread.sleep(2000);
		js.executeScript("arguments[0].value=arguments[1];", this.getLocationInput(), location);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", this.getSubmit());
		Thread.sleep(2000);
	}
	
//	metodu koja zatvara iskačući dijalog, klikom na X dugme
	public void closePopup () {
		this.getCloseElement().click();
	}
	
	
	
}
