package com.weather.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenWeatherMapInvalidCityTest {

	private WebDriver driver;
	private Properties properties = new Properties();
	public static Logger LOG = Logger.getLogger(OpenWeatherMapInvalidCityTest.class);

	@BeforeMethod
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		properties.load(new FileReader(new File("test.properties")));
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	//This test will test the error message shown by the website whenever the invalid city is entered by the user
	
	@Test
	public void testWeatherInvalidCityError() throws Exception {

		LOG.info("Open OpenWeatherMap Website");
		driver.get("https://openweathermap.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LOG.info("Verifying Website is correctly Opened");
		try {
			driver.getTitle().contains("OpenWeatherMap");
			LOG.info("Website title is as expected");
		} catch (Exception e) {
			Assert.fail("Website title is not as expected");
		}

		LOG.info("Verifying City Name Textbox on the WebPage");
		try {
			WebElement citySearchBox = driver
					.findElement(By.cssSelector("input#q.form-control.border-color.col-sm-12"));
			citySearchBox.isDisplayed();
			LOG.info("City Name Text Box is displayed.");
			LOG.info("Enter invalid city name in Your City Name checkbox");
			citySearchBox.sendKeys("xyz");
			LOG.info("Click on Search Button");
			driver.findElement(By.xpath("//*[@class='fa fa-question-circle']")).click();
		} catch (Exception e) {
			Assert.fail("Searching the city is halted due to the specified exception: " + e.getMessage());
		}

		LOG.info("Verifying Weather in your city Page is correctly Opened");
		try {
			driver.findElement(By.xpath("//*[text()='Weather in your city']")).isDisplayed();
			LOG.info("Weather in Your City Page is displayed.");
		} catch (Exception e) {
			Assert.fail("Weather in Your City Page is not displayed.");
		}

		LOG.info("Verifying Error Message on the Search Page");
		try {
			driver.findElement(By.xpath("//div[@class='alert alert-warning']")).isDisplayed();
			LOG.info("Alert on the Search Page is displayed.");
			String expectedErrorMessage = "Not found";
			String actualErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-warning']")).getText();
			Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
			LOG.info("Error Message on the Search Page is correctly displayed.");
		} catch (Exception e) {
			Assert.fail("Alert on the Search Page is not displayed.");
		}

	}

}