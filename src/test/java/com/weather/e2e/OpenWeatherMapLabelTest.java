package com.weather.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenWeatherMapLabelTest {
	private WebDriver driver;
	public static Logger LOG = Logger.getLogger(OpenWeatherMapLabelTest.class);

	@BeforeMethod
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	//This test will verify all the important labels present on the home page of the website.
	
	@Test
	public void testWeatherLabels() throws Exception {

		LOG.info("Open OpenWeatherMap Website");
		driver.get("https://openweathermap.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LOG.info("Verifying Website Title");
		try {
			driver.getTitle().contains("OpenWeatherMap");
		} catch (Exception e) {
			Assert.fail("Website title is not as expected");
		}

		LOG.info("Verifying Website Name is being displayed on the top left side");
		try {
			driver.findElement(By.xpath("//*[@alt='openweathermap']")).isDisplayed();
			LOG.info("Website Name is being displayed on the top left side.");
		} catch (Exception e) {
			Assert.fail("Website name is not being displayed on the webpage.");
		}

		try {
			driver.findElement(By.xpath("//*[@class='slide-button']")).isDisplayed();
			LOG.info("Celcius/Farnheit Toggle is displayed.");
		} catch (Exception e) {
			Assert.fail("Celcius/Farnheit Toggle is not being displayed.");
		}

		LOG.info("Verifying Different Labels on the WebPage");
		try {
			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'Weather')]"))
					.isDisplayed();
			LOG.info("Weather Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'API')]")).isDisplayed();
			LOG.info("API Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'Price')]"))
					.isDisplayed();
			LOG.info("Price Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'Partners')]"))
					.isDisplayed();
			LOG.info("Partners Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'Stations')]"))
					.isDisplayed();
			LOG.info("Stations Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'Widgets')]"))
					.isDisplayed();
			LOG.info("Widgets Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item') and contains(.//a, 'Blog')]"))
					.isDisplayed();
			LOG.info("Blog Label is displayed.");

			driver.findElement(By.xpath("//li[contains(@class, 'nav__item dropdown') and contains(.//a, 'Maps')]"))
					.isDisplayed();
			LOG.info("Maps Dropdown Label is displayed.");

		} catch (Exception e) {
			Assert.fail("Missing Label");
		}

		LOG.info("Verifying City Name Textbox on the WebPage");
		try {
			driver.findElement(By.xpath("//*[@class='form-group search-cities__block']")).isDisplayed();
			LOG.info("City Name Text Box is displayed.");
		} catch (Exception e) {
			Assert.fail("City Name Text Box is not displayed.");
		}

		LOG.info("Verifying Search Button on the WebPage");
		try {
			driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed();
			LOG.info("Search Button is displayed.");
		} catch (Exception e) {
			Assert.fail("Search Button is not displayed.");
		}

		LOG.info("Verifying Current Location Link on the WebPage");
		try {
			driver.findElement(By.xpath("//button[@class='btn search-cities__lnk']")).isDisplayed();
			LOG.info("Current Location Link is displayed.");
		} catch (Exception e) {
			Assert.fail("Current Location Link is not displayed.");
		}

		LOG.info("Verifying Sign In Link on the WebPage");
		try {
			driver.findElement(By.xpath("(//a[@class='pull-right'])[2]")).isDisplayed();
			LOG.info("Sign In Link is displayed.");
		} catch (Exception e) {
			Assert.fail("Sign In Link is not displayed.");
		}

		LOG.info("Verifying Sign Up Link on the WebPage");
		try {
			driver.findElement(By.xpath("(//a[@class='pull-right'])[1]")).isDisplayed();
			LOG.info("Sign Up Link is displayed.");
		} catch (Exception e) {
			Assert.fail("Sign Up Link is not displayed.");
		}

		LOG.info("Verifying Weather in your city search box on the WebPage");
		try {
			driver.findElement(By.xpath("(//a[@class='pull-right'])[3]")).isDisplayed();
			LOG.info("Weather in your city search box is displayed.");
		} catch (Exception e) {
			Assert.fail("Weather in your city search box is not displayed.");
		}

		LOG.info("Verifying Support Center Link on the WebPage");
		try {
			driver.findElement(By.xpath("//span[@class='hidden-xs']")).isDisplayed();
			LOG.info("Support Center Link is displayed.");
		} catch (Exception e) {
			Assert.fail("Support Center Link is not displayed.");
		}

		LOG.info("Verifying Current weather and forecasts in your city on the WebPage");
		try {
			driver.findElement(By.xpath("//*[text()='Current weather and forecasts in your city']")).isDisplayed();
			LOG.info("Current weather and forecasts in your city is displayed.");
		} catch (Exception e) {
			Assert.fail("Current weather and forecasts in your city is not displayed.");
		}

		LOG.info("Verifying Main Tab on the WebPage");
		try {
			driver.findElement(By.xpath("//li[contains(@class, 'widget-tabs__item') and contains(.//a, 'Main')]"))
					.isDisplayed();
			LOG.info("Main Tab is displayed.");
		} catch (Exception e) {
			Assert.fail("Main Tab is not displayed.");
		}

		LOG.info("Verifying Daily Tab on the WebPage");
		try {
			driver.findElement(By.xpath("//li[contains(@class, 'widget-tabs__item') and contains(.//a, 'Daily')]"))
					.isDisplayed();
			LOG.info("Daily Tab is displayed.");
		} catch (Exception e) {
			Assert.fail("Daily Tab is not displayed.");
		}

		LOG.info("Verifying Hourly Tab on the WebPage");
		try {
			driver.findElement(By.xpath("//li[contains(@class, 'widget-tabs__item') and contains(.//a, 'Hourly')]"))
					.isDisplayed();
			LOG.info("Hourly Tab is displayed.");
		} catch (Exception e) {
			Assert.fail("Hourly Tab is not displayed.");
		}

		LOG.info("Verifying Map Tab on the WebPage");
		try {
			driver.findElement(By.xpath("//li[contains(@class, 'widget-tabs__item') and contains(.//a, 'Map')]"))
					.isDisplayed();
			LOG.info("Map Tab is displayed.");
		} catch (Exception e) {
			Assert.fail("Map Tab is not displayed.");
		}

		LOG.info("Verifying Weather in London, GB");
		try {
			driver.findElement(By.xpath("//*[@class = 'weather-widget__city-name']")).isDisplayed();
			LOG.info("Weather in London, GB is displayed.");
		} catch (Exception e) {
			Assert.fail("Weather in London, GB is not displayed.");
		}

	}

}
