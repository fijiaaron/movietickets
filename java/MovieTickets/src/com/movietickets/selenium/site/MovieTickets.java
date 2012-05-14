package com.movietickets.selenium.site;

import com.movietickets.selenium.pages.HomePage;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MovieTickets {

	public static String urlBase = "http://www.movietickets.com";
	
	protected Selenium selenium;
	
	public MovieTickets() {
		setSelenium(createSelenium(urlBase));
	}
	
	public MovieTickets(Selenium selenium) {
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

	public HomePage HomePage() { return new HomePage(selenium); }
	
}
