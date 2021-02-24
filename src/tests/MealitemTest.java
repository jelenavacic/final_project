package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealitemTest extends BasicTest {
	
//		U okviru add meal to cart testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		ugasite lokacioni iskačući dijalog
//		dodajte jelo u korpu, količina je proizvoljna
//		verifikujte da je prikazana poruka sa tekstom 
//		"The Following Errors Occurred:
//		Please Select Location"
//		sačekajte da obaveštenje nestane
//		postavite lokaciju na "City Center - Albany"
//		dodajte jelo u korpu, količina je proizvoljna
//		verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"

	
	@Test (priority = 0)
	
	public void addMealToCart () throws InterruptedException {
		driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		SoftAssert sa = new SoftAssert ();
		lpp.closePopup();
		
		mp.addToCart("3");
		boolean setLocation1st = nsp.messageText().contains("The Following Errors Occurred:");
		sa.assertTrue(setLocation1st, "[ERROR] Location is preselected!");
		
//		sačekajte da obaveštenje nestane
		nsp.waiting();
		
		lpp.setLocation("City Center - Albany");
		
		mp.addToCart("3");
		Thread.sleep(1000);
		
		boolean addTocartMsg = nsp.messageText().contains("Meal Added To Cart");
		sa.assertTrue(addTocartMsg, "[ERROR] The meal is NOT added!");
		
		sa.assertAll();

	}
	
	
//		U okviru add meal to favorite testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		ugasite lokacioni iskačući dijalog
//		dodajte jelo u omiljena jela
//		verifikujte da je prikazana poruka sa tekstom "Please login first!"
//		učitajte stranicu za prijavu
//		prijavite se na aplikaciju preko demo naloga
//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		dodajte jelo u omiljena jela
//		verifikujte da je prikazana poruka sa tekstom "Product has been added to your favorites."


	@Test (priority = 5)
	
	public void addMealTofavorite () throws InterruptedException {
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		SoftAssert sa = new SoftAssert ();
		//lpp.closePopup();
		
		mp.addToFavorite();
		boolean login = nsp.messageText().contains("Please login first!");
		sa.assertTrue(login, "[ERROR] User already logged in!");
		
		driver.navigate().to(baseUrl + "guest-user/login-form");
		lp.login(email, password);
		
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mp.addToFavorite();
		boolean favorite = nsp.messageText().contains("Product has been added to your favorites.");
		sa.assertTrue(favorite, "[ERROR] The meal is NOT added to favorite!");
		Thread.sleep(2000);
		
		sa.assertAll();
	}
	
	
//		U okviru clear cart testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/meals
//		postavite lokaciju na "City Center - Albany"
//		čitate podatke iz xlsx fajla > Meals Sheet 
//		u korpu dodajte svaki proizvod sa određenom količinom
//		za svako dodavanje proizvioda verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"
//		koristite SoftAssert za ovu proveru
//		obrišite sve stavke iz korpe
//		verifikujte da je prikazana poruka sa tekstom "All meals removed from Cart successfully"

	@Test (priority = 10) 
	
	public void clearCart () throws InterruptedException, IOException {
		driver.navigate().to(baseUrl + "meals");
		SoftAssert sa = new SoftAssert ();
		
		lpp.closePopup();
		Thread.sleep(1000);
		lpp.setLocation("City Center - Albany");
		
		File file = new File ("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet2 = wb.getSheetAt(1);
		
		for (int i = 1; i <= sheet2.getLastRowNum(); i++) {
			XSSFRow row = sheet2.getRow(i);
			
			String mealURL = row.getCell(0).getStringCellValue();
			int qty = (int) row.getCell(1).getNumericCellValue();
			
			driver.navigate().to(mealURL);
			mp.addToCart(Integer.toString(qty));
			
			boolean addTocartMsg = nsp.messageText().contains("Meal Added To Cart");
			sa.assertTrue(addTocartMsg, "[ERROR] The meal is NOT added!");
			
		}
		
	
		csp.clearAll();
		boolean removeAll = nsp.messageText().contains("All meals removed from Cart successfully");
		sa.assertTrue(removeAll, "[ERROR] Meals are still in the Cart!");
		
		sa.assertAll();
		fis.close();
		wb.close();
		
	}
	
	
}
