package pages;

import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CommonPage {
	
	public CommonPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

}
