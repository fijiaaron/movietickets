package com.movietickets.selenium.pages;

import com.thoughtworks.selenium.Selenium;

public class MovieTicketsPage {
	
	public static String urlBase = "http://www.movietickets.com";	
	
	protected Selenium selenium;
	
	public MovieTicketsPage() {}
	public MovieTicketsPage(Selenium s) { this.selenium = s; }
	
	public String getTitle() {
		return selenium.getTitle();
	}
}
