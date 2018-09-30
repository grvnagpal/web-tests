package com.weather.e2e;

import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenWeatherMapBrokenLinkTest {
	private static WebDriver driver;
	public static Logger LOG = Logger.getLogger(OpenWeatherMapBrokenLinkTest.class);

	@BeforeTest
	public void setUp() throws Exception {
		BasicConfigurator.configure()
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                WebDriver driver = new ChromeDriver(chromeOptions);
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

	//This test will validate all the links present on the webpage and will give the message if the link is working OK or not.
	
	@Test
	public static void testBrokenLinksOnOpenWeatherMap() throws Exception {
		LOG.info("Opening OpenWeatherMap.com");
		driver.get("https://openweathermap.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> allImages = findAllLinks(driver);
		LOG.info("Total number of links found " + allImages.size());
		LOG.info("Links Output: ");
		System.out.println();
		for (WebElement element : allImages) {
			try {
				System.out.println("URL: " + element.getAttribute("href") + " RETURNED "
						+ isLinkBroken(new URL(element.getAttribute("href"))));
			} catch (Exception exp) {
				System.out.println(
						"At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());
			}
		}
	}

	public static List<WebElement> findAllLinks(WebDriver driver) {
		List<WebElement> elementList = new ArrayList<WebElement>();
		elementList = driver.findElements(By.tagName("a"));
		elementList.addAll(driver.findElements(By.tagName("img")));
		List<WebElement> finalList = new ArrayList<WebElement>();
		for (WebElement element : elementList) {
			if (element.getAttribute("href") != null) {
				finalList.add(element);
			}
		}
		return finalList;
	}

	public static String isLinkBroken(URL url) throws Exception {
		String response = "";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.connect();
			response = connection.getResponseMessage();
			connection.disconnect();
			return response;
		} catch (Exception exp) {
			return exp.getMessage();
		}
	}

}
