package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {
	
//	(Bonus deo)
//	Search Test:
//	U okviru search results testa potrebno je izvršiti sledeće korake:
//	učitajte stranicu http://demo.yo-meals.com/meals
//	postavite lokaciju na "City Center - Albany"
//	čitate podatke iz xlsx fajla > Meals Search Results Sheet
//	izvršite otvaranje svakog linka i postavljanje svake lokacije
//	i za svaku stranicu proverite rezultate pretrage 
//	verifikujte da je broj rezultata na stranici isti kao u fajlu
//	verifikujte da je svaki rezultat na stranici redom prikazan kao u fajlu
//	
//	Na kraju kreirajte testng.xml fajl u kom ćete pobrojati sve testove na izvršenje.

	
	@Test 
	
	public void search () throws InterruptedException, IOException {
		
		driver.navigate().to(baseUrl + "meals");
		SoftAssert sa = new SoftAssert ();
		
		lpp.closePopup();
		Thread.sleep(1000);
		lpp.setLocation("City Center - Albany");
		
		File file = new File ("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		for (int i = 1; i < 7; i++) {
			XSSFRow row = sheet.getRow(i);
			
			String location = row.getCell(0).getStringCellValue();
			String url = row.getCell(1).getStringCellValue();
			int noOfResults = (int) row.getCell(2).getNumericCellValue();
			
			driver.navigate().to(url);
			lpp.setLocation(location);
			
			sa.assertEquals(srp.numberOfResults(), noOfResults, "[ERROR] Number of results is not the same!");
			
//			for (int j = 3; j < row.getLastCellNum(); j++) {
//				String meal = row.getCell(j).getStringCellValue();
//				sa.assertTrue(srp.mealName().contains(meal), "[ERROR] Meal is not in the list!");
//				
//			}
			
		}
		sa.assertAll();
		fis.close();
		wb.close();
		
	}
	
	

}
