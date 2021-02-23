package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {
	
	@Test (priority = 0)
	
//		U okviru edit profile testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
//		ugasite lokacioni iskačući dijalog
//		prijavite se na aplikaciju preko demo naloga
//		verifikujte da je prikazana poruka sa tekstom "Login Successfull"
//		učitajte stranicu http://demo.yo-meals.com/member/profile
//		zamenite sve osnovne informacije korisnika
//		verifikujte da je prikazana poruka sa tekstom "Setup Successful"
//		odjavite se sa sajta
//		verifikujte da je prikazana poruka sa tekstom "Logout Successful!"

	
	public void editProfile () throws InterruptedException {
		driver.navigate().to(this.baseUrl + "guest-user/login-form");
		SoftAssert sa = new SoftAssert ();
		
		lpp.closePopup();
		lp.login(this.email, this.password);
		
		boolean login = lp.getMessage().contains("Login Successfull");
		sa.assertTrue(login, "[ERROR] Login failed!");
		
		driver.navigate().to(this.baseUrl + "member/profile");
		
		pp.profileSetup("Roland", "Dubois", "222", "99922224", "22033", "United Kingdom", "Aberdeen", "Cleveland");
		boolean setup = pp.getMessage().contains("Setup Successful");
		
		sa.assertTrue(setup, "[ERROR] Setup failed!");
		
		pp.logout();
		boolean logout = pp.getMessage().contains("Logout Successfull!");
		sa.assertTrue(logout, "[ERROR] Logout failed!");
		
		sa.assertAll();
		
	}

	
//		U okviru change profile image testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
//		ugasite lokacioni iskačući dijalog
//		prijavite se na aplikaciju preko demo naloga
//		verifikujte da je prikazana poruka sa tekstom "Login Successfull"
//		učitajte stranicu http://demo.yo-meals.com/member/profile
//		otpremite profilnu sliku
//		sliku iz images foldera
//		s obzirom na to da se za otpremanje šalje apsolutna putanja do slike, 
//		a mi koristimo relativnu, moramo da pribavimo putanju na sledeći način
//		String imgPath = new File("imagеs/slika.png").getCanonicalPath();
//		Koristan link
//		verifikujte da je prikazana poruka sa tekstom "Profile Image Uploaded Successfully"
//		sačekajte da nestane obaveštenje
//		obrišite profilnu sliku
//		verifikujte da je prikazana poruka sa tekstom "Profile Image Deleted Successfully"
//		sačekajte da nestane obaveštenje
//		odjavite se sa sajta
//		verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"

	@Test (priority = 5)
	
	public void editProfileImage () throws IOException, InterruptedException {
		driver.navigate().to(baseUrl + "guest-user/login-form");
		SoftAssert sa = new SoftAssert ();
		
		lpp.closePopup();
		lp.login(this.email, this.password);
		
		boolean login = lp.getMessage().contains("Login Successfull");
		sa.assertTrue(login, "[ERROR] Login failed!");
		driver.navigate().to(this.baseUrl + "member/profile");
		
		String imgPath = new File("img/profileImg.jpg").getCanonicalPath();
		pp.uploadPicture(imgPath);
		
		boolean profilePicUpload = pp.getMessage().contains("Profile Image Uploaded Successfully");
		sa.assertTrue(profilePicUpload, "[ERROR] Profile picture upload failed!");
		
//		sačekajte da nestane obaveštenje
		//waiter.until(ExpectedConditions.invisibilityOf());
		pp.waiting();
		
		pp.removePicture();
		boolean removeProfilePic = pp.getMessage().contains("Profile Image Deleted Successfully");
		sa.assertTrue(removeProfilePic, "[ERROR] Profile picture deletion failed!");
		pp.waiting();
		
		//waiter.until(arg0)
		
		pp.logout();
		boolean logout = pp.getMessage().contains("Logout Successfull!");
		sa.assertTrue(logout, "[ERROR] Logout failed!");
		
		sa.assertAll();
		
	
	}
}
