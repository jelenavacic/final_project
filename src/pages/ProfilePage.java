package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

//		Profile Page:
//		get metode za sve potrebne elemente
//		sve elemente za izmenu osnovnih informacija kao i 

//		sve elemente potrebne za rad sa profilnom slikom	
//		da bi se na stranici pojavio element input type="file" 
//		potrebno je da se prvo izvrši JavaScript kod koji vrši akciju klik na Upload dugme 
//		Skripta: arguments[0].click();
//		metodu koja otprema profilnu sliku - kao parametar se prosleđuje putanja do fajla, 
//		tj. u ovom slučaju do slike
//		metodu koja briše profilnu sliku
//		klikom na Remove dugme 
//		Preporuka: izvršite JavaScript kod arguments[0].click(); nad tim dugmetom
//		metodu koja menja sve osnovne informacije korisnika - kao parametri se prosleđuju sve potrebne informacije

	public WebElement getFirstname() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastname() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAdress() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement elemId = driver.findElement(By.name("user_country_id"));
		return new Select(elemId);
	}

	public Select getState() {
		WebElement elemId = driver.findElement(By.name("user_state_id"));
		return new Select(elemId);
	}

	public Select getCity() {
		WebElement elemId = driver.findElement(By.name("user_city"));
		return new Select(elemId);
	}

	public WebElement getFirstSaveBtn() {
		return driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}

	public WebElement getCurrentPassword() {
		return driver.findElement(By.name("current_password"));
	}

	public WebElement getNewPassword() {
		return driver.findElement(By.name("new_password"));
	}

	public WebElement getConfirmPassword() {
		return driver.findElement(By.name("conf_new_password"));
	}

	public WebElement getSecondSaveBtn() {
		return driver.findElement(By.xpath("//*[@id=\"frm_fat_id_changePwdFrm\"]/div/div[4]/fieldset/input"));
	}

	public WebElement getUploadBtn() {
		return driver.findElement(By.xpath("//a[@title='Uplaod']"));
	}

	public WebElement getRemoveBtn() {
		return driver.findElement(By.className("remove"));
	}
	
	public void uploadPicture (String pictureUrl) throws InterruptedException {
		js.executeScript("arguments[0].click();", this.getUploadBtn());
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pictureUrl);
		Thread.sleep(2000);
	}
	
	public void removePicture () throws InterruptedException {
		js.executeScript("arguments[0].click();", this.getRemoveBtn());
		Thread.sleep(2000);
	}
	

	public String getMessage() {
		return driver.findElement(By.xpath("/html/body/div[2]")).getText();
	}

	public void waiting() {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[2]")));
	}


//	metodu koja menja sve osnovne informacije korisnika - kao parametri se prosleđuju sve potrebne informacije

	public void profileSetup(String firstName, String lastName, String adress, String phone, String zipCode,
			String country, String state, String city) throws InterruptedException {
		getFirstname().clear();
		getFirstname().sendKeys(firstName);

		getLastname().clear();
		getLastname().sendKeys(lastName);

		getAdress().clear();
		getAdress().sendKeys(adress);

		getPhoneNo().clear();
		getPhoneNo().sendKeys(phone);

		getZipCode().clear();
		getZipCode().sendKeys(zipCode);

		getCountry().selectByVisibleText(country);
		Thread.sleep(2000);
		getState().selectByVisibleText(state);
		Thread.sleep(2000);
		getCity().selectByVisibleText(city);
		Thread.sleep(2000);
		getFirstSaveBtn().click();
		Thread.sleep(2000);
	}

}
