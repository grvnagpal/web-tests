package com.weather.e2e;

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

public class OpenWeatherMapValidCityTest {
	private WebDriver driver;
	private static Logger LOG = Logger.getLogger(OpenWeatherMapValidCityTest.class);
	private static String cityName = "Delhi, IN";
	
	@BeforeMethod
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chrome/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	//This test will search the city - Delhi and show its weather.

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
			LOG.info("Enter Valid city name in Your City Name checkbox");
			citySearchBox.sendKeys(cityName);
			LOG.info("Click on Search Button");
			driver.findElement(By.xpath("//*[@class='fa fa-question-circle']")).click();
		} catch (Exception e) {
			Assert.fail("Searching the city is halted due to the specified exception: " + e.getMessage());
		}
		
		LOG.info("Verifying City Name is displayed on the Search Results");
		WebElement firstSearchResult = driver.findElement(By.xpath("//*[@id='forecast_list_ul']/table/tbody/tr/td[2]/b[1]/a"));
		String actualCityName = firstSearchResult.getText();
		Assert.assertEquals(actualCityName, cityName);
		
		LOG.info("Click on the City Name");
		firstSearchResult.click();
		
		LOG.info("Verify the Weather on the selected city is displayed");
		try {
			WebElement weatherCityName = driver.findElement(By.xpath("//*[@class = 'weather-widget__city-name']"));
			String weatherCity = weatherCityName.getText();
			Assert.assertTrue(weatherCity.contains(cityName));
			LOG.info("City Name is displayed as expected.");
		}catch(Exception e) {
			Assert.fail("City Name is not as expected");
		}
		
		LOG.info("Verifying Actual Weather is displayed in the city");
		try {
			driver.findElement(By.xpath("//*[@class='weather-widget__temperature']")).isDisplayed();
			LOG.info("Weather is displayed on the Weather Search Result Page.");
		}catch(Exception e) {
			Assert.fail("Weather is not displayed on the Weather Search Result Page.");
		}

	}
}
