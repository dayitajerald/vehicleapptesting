package vehicleapp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VehicleAppTest {
	
	private WebDriver driver;
    private LoginPage loginPage;
    private String baseUrl = "http://localhost:5173";
    private BookingPage bookingPage;
    private DashboardPage dashboardPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;
    private ReviewPage reviewPage;
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        bookingPage = new BookingPage(driver);
        dashboardPage = new DashboardPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);
        reviewPage = new ReviewPage(driver);
    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"testw1","1234"},
            {"Invalid","invalid"}
            
        };
    }
    
    @DataProvider(name = "paymentData")
    public Object[][] getPaymentData() {
        return new Object[][] {
            {"1500"}
        };
    }
    
    
    @DataProvider(name = "updateProfileData")
    public Object[][] getUpdateData() {
        return new Object[][] {
            {"Test Worker", "testing@gmail.com", "1234569874"}
        };
    }
    
    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {
        return new Object[][] {
            {"Jane S", "janes", "janes@smith.com", "jane@123", "0987654321", 1, 2},
        };
    }
    @Test(dataProvider = "registerData", priority=0)
    public void testRegister(String name, String username, String email, String password, String phone, int typeIndex, int serviceIndex) throws InterruptedException {
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/a")).click();
    	Thread.sleep(1000);
        System.out.println("Entering the credentials for registration");
        registerPage.setName(name);
        registerPage.setUsername(username);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.setPhone(phone);
        registerPage.setType(typeIndex);
        registerPage.setService(serviceIndex);
        registerPage.clickRegisterButton();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/");
        System.out.println("After successful register, redirected to login page.");
    }




    @Test(dataProvider = "loginData", priority=1)
    public void testLogin(String username, String password) throws InterruptedException{
    	System.out.println("Entering the credentials for login");
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/worker/dashboard");
        System.out.println("After successful login, redirected to home");
    }
   
    
    @Test(dataProvider = "updateProfileData", priority=2)
    public void testUpdateProfile(String name, String email, String phone) throws InterruptedException{
        driver.get(baseUrl + "/worker/dashboard");
        Thread.sleep(2000);
        profilePage.clickProfileImage();
        Thread.sleep(2000);
        profilePage.clickProfileLink();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/worker/profile");
        Thread.sleep(2000);
        profilePage.clickEditProfile();
        Thread.sleep(2000);
        profilePage.setName(name);
        profilePage.setEmail(email);
        profilePage.setPhone(phone);
        Thread.sleep(2000);
        profilePage.clickUpdateProfile();
        Thread.sleep(2000);
        profilePage.clickSaveProfile();
        Thread.sleep(2000);
        driver.get(baseUrl + "/worker/profile");
        Thread.sleep(2000);
        Assert.assertEquals(profilePage.getName(), "Test Worker");
        Assert.assertEquals(profilePage.getEmail(), "testing@gmail.com");
        Assert.assertEquals(profilePage.getPhone(), "1234569874");
        Thread.sleep(2000);
    }
    
    @Test(priority=3)
    public void testDashboard() throws InterruptedException{
    	driver.get(baseUrl + "/worker/dashboard");
		Thread.sleep(3000);
		System.out.println("Accpeting a service");
		dashboardPage.clickAcceptButton();
		Thread.sleep(1000);
		
//		System.out.println("Rejecting a service");
//		dashboardPage.clickRejectButton();
//		Thread.sleep(1000);
		
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/worker/dashboard");
        Thread.sleep(1000); 
    }
    
    @Test(dataProvider = "paymentData", priority=4)
    public void testPayment(String amt) throws InterruptedException{
		Thread.sleep(3000);
		driver.get(baseUrl + "/worker/bookings");
		Thread.sleep(3000);
		bookingPage.clickConfirmButton();
		Thread.sleep(3000);
		bookingPage.enterAmount(amt);
		Thread.sleep(3000);
		bookingPage.clickEnterBtn();
	
    }
    
    @Test(priority=5)
    public void testReview(int starReview, String comment) throws InterruptedException{
		Thread.sleep(3000);
		driver.get(baseUrl + "/worker/reviews");
		
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
