package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class AmazonHomepage {
	
	// The purpose of this constructor is that whenever we create an object of this class,
	// we are initializing the page factory 
	// and initializing the webElements within this class with the object of the driver we have.
	
	public AmazonHomepage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy (id = "twotabsearchtextbox")
	public WebElement amazonSearchBox;
	
	@FindBy (xpath = "//input[@id='nav-search-submit-button']")
	public WebElement amazonSearchButton;

}
