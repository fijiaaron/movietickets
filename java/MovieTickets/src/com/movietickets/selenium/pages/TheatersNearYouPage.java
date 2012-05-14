package com.movietickets.selenium.pages;

import com.thoughtworks.selenium.Selenium;

public class TheatersNearYouPage extends MovieTicketsPage {
	public static String numberOfTicketingTheaters = "//div[@id='mid']/div[1]/table/tbody/tr[1]/td[1]/p";
	public static String theaterList = "//ul[@id='tkdrow'][2]/li";
	
	/**
	 * Expected values
	 */
	public static class Expected {
		public static String title = "Movie Theaters Nearest to You - MovieTickets.com";
	}
	
	
	/**
	 * Constructors
	 */
	public TheatersNearYouPage() {
		super();
	}
	public TheatersNearYouPage(Selenium selenium) {
		super(selenium);
	}
	
	
	/**
	 * Get the locator for a theater in the list of theaters near you.
	 * 
	 * @param int number
	 * @return String locator
	 */
	public String getTheater(int number) {
		String locator = selenium.getText(theaterList + "[" + String.valueOf(number) + "]" + "/a");
		return locator;
	}
}
