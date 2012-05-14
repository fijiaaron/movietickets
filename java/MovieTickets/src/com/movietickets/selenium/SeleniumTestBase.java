package com.movietickets.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class SeleniumTestBase {
	
	public static String urlBase = "http://www.movietickets.com";
	public static String TIMEOUT = "60000"; // 1 minute
	
	protected Selenium selenium;
	protected WebDriver driver;
	
	public SeleniumTestBase() {
		this.selenium = createSelenium(urlBase);
	}
	
	public SeleniumTestBase(Selenium selenium) {
		setSelenium(selenium);
	}
	
	public static Selenium createSelenium(String urlBase) {
		WebDriver driver = new FirefoxDriver();
		Selenium selenium = new WebDriverBackedSelenium(driver, urlBase); 
		
		return selenium;
	}
	
	public void setSelenium(Selenium selenium) {
		this.selenium = selenium;
	}
}
