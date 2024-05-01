package vehicleapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage {
	
	private WebDriver driver;
	private By confirmBtn = By.cssSelector("#root > div > div > div > table > tbody > tr > th > button");
	private By amtInput = By.xpath("//*[@id=\"payModal\"]/div/input");
	private By enterBtn = By.xpath("//*[@id=\"payModal\"]/div/div/form/button");
	
	public BookingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickConfirmButton() {
		driver.findElement(confirmBtn).click();
	}
	
	public void enterAmount(String amount) {
		driver.findElement(amtInput).sendKeys(amount);
	}
	
	public void clickEnterBtn() {
		driver.findElement(enterBtn).click();
	}
	
	
}
