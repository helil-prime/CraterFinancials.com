package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;


public class Driver {
	
	public static WebDriver driver;
	static ChromeOptions chromeOptions;
	static URL url;

		public static WebDriver getDriver() {
			String browser = System.getProperty("browser");
			if (browser == null) {
				browser = TestDataReader.getProperty("browser_type");
			}
			if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
				switch(browser) {
				case "chrome-headless" :
				    chromeOptions = new ChromeOptions();
					chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					chromeOptions.addArguments("--headless");
					driver = new ChromeDriver(chromeOptions);
					break;
				case "chrome" :
					driver = new ChromeDriver();
					break;
				case "firefox" :
					driver = new FirefoxDriver();
					break;
				case "firefox-headless" :
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.addArguments("--headless");
					driver = new FirefoxDriver(firefoxOptions);
					break;
				case "safari" :
					driver = new SafariDriver();
					break;
				case "saucelabs" :
					SafariOptions browserOptions = new SafariOptions();
					browserOptions.setPlatformName("macOS 10.15");
					browserOptions.setBrowserVersion("15");
					Map<String, Object> sauceOptions = new HashMap<>();
					sauceOptions.put("username", "helil-batch5");
					sauceOptions.put("accessKey", "yourAccessKey");
					sauceOptions.put("build", "003");
					sauceOptions.put("name", "macos10_exc1");
					browserOptions.setCapability("sauce:options", sauceOptions);
					try {
						url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver = new RemoteWebDriver(url, browserOptions);
					break;
				case "edge" :
					driver = new EdgeDriver();
					break;
				default:
					ChromeOptions Options = new ChromeOptions();
					Options.addArguments("--headless");
					driver = new ChromeDriver(Options);
					break;
				}
			}
			return driver;
		}
		
		public static void quitDriver() {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
	    }

}
