package vehicleapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
	private WebDriver driver;
	private By acceptBtn = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/table/tbody/tr/th/button[1]");
	private By rejectBtn = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/table/tbody/tr/th/button[2]");
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAcceptButton() {
		driver.findElement(acceptBtn).click();
	}
	
	public void clickRejectButton() {
		driver.findElement(rejectBtn).click();
	}
}
