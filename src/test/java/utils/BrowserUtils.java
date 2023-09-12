package utils;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {
	
	
	WebDriverWait wait;
	Actions act;
	
	public void waitUntilElementVisibleWithLocator(By locator) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeInputed(WebElement element, String text) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public void waitForAllElementsToBeVisible(List<WebElement> element) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	//This method tells the driver to wait until an element is clickable
		public void waitUntilElementClickable(WebElement element) {
			wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(7));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		public void waitUntilElementClickableWithLocator(By locator) {
			wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(7));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
	
	public void sendkeysWithActionsClass(WebElement element, String input) {
		act = new Actions(Driver.getDriver());
		act.sendKeys(element, input).build().perform();
	}
	
	public void moveToWithActionsClass(WebElement element) {
		act = new Actions(Driver.getDriver());
		act.moveToElement(element).build().perform();
	}
	
	public void clickWithActionsClass(WebElement element) {
		act = new Actions(Driver.getDriver());
		act.click(element).build().perform();
	}
	
	
	// this method generate 3 digit random number
		public int randomNumber() {
			Random rand = new Random();
			int randomNum = rand.nextInt((999 - 100) + 1) + 100;
			return randomNum;
		}
	

}
