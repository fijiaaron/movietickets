package com.movietickets.selenium.pages;

import com.thoughtworks.selenium.Selenium;

public class HomePage extends MovieTicketsPage {
	
	public static String url = "/";
	
	public static String logo = "//image[@title='MovieTickets.com'";
	public static String searchTitle = "srchTitle";
	public static String zipCodeBox = "Szip";
	public static String searchButton = "zGo"; 
	public static String selectMovie = "//a[@class='btn_slide1']";
	public static String emailAddress = "cust_email";
	
	public static String topMoviesList = "//div[@class='footerList1']";
	public static String topMoviesHeader = "//div[@class='footerExtendHd']";
	

	/**
	 * Expected values 
	 */
	public static class Expected {
		public static String title = "Movie Tickets, Theaters, Showtimes, Trailers - MovieTickets.com";
	}
	
	
	/**
	 * Constructors
	 */
	public HomePage() {
		super();
	}
	public HomePage(Selenium selenium) {
		super(selenium);
	}
	
	
	/**
	 * Open the MovieTickets.com home page.
	 * 
	 * @return HomePage
	 */
	public HomePage open() {
		selenium.open(url);
		selenium.windowMaximize();
		return this;
	}
	
	
	public TheatersNearYouPage searchByZipCode(String zipCode) {	
		selenium.type(zipCodeBox, zipCode);
		selenium.click(searchButton);

		return new TheatersNearYouPage(selenium);
	}
	
	/**
	 * Get the locator for the top movie by rank.
	 * 
	 * @param number
	 * @return
	 */
	public String getTopMovie(int number) {
		String locator = topMoviesList + "/" + "/ul/li[" + String.valueOf(number) + " ]/a";
		return locator;
	}
	public String getTopMovie() {
		return getTopMovie(1);
	}
}
