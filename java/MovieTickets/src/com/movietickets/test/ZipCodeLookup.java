package com.movietickets.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.movietickets.selenium.SeleniumTestBase;
import com.movietickets.selenium.pages.HomePage;
import com.movietickets.selenium.pages.TheatersNearYouPage;
import com.movietickets.selenium.site.MovieTickets;


/**
 * This test exercises the zip code lookup feature
 * 
 * @author Aaron Evans <aarone@one-shore.com>
 */
@RunWith(Parameterized.class)
public class ZipCodeLookup extends SeleniumTestBase {
	
	MovieTickets movieTickets;
	HomePage homePage;
	
	private String zipCode;
	private int expectedNumberOfTicketingTheaters;
	private List<String> expectedListOfTheaters;
	
	/**
	 * Constructor 
	 * 
	 * @param zipCode
	 * @param numberOfTicketingTheaters
	 * @param listOfTheaters
	 */
	public ZipCodeLookup(String zipCode, int numberOfTicketingTheaters, List<String>listOfTheaters) {
		this.zipCode = zipCode;
		this.expectedNumberOfTicketingTheaters = numberOfTicketingTheaters;
		this.expectedListOfTheaters = listOfTheaters;
	}
	
	
	/**
	 * This is a list of zip codes to test including
	 * number of ticketing theaters and list of theaters near you
	 * 
	 * @return Collection<Object[]>
	 */
	@Parameters
	public static Collection<Object[]> getTop10Movies() {
		
		ArrayList<String> SeattleTheaters = new ArrayList<String>(
			Arrays.asList(
				"Seattle Art Museum - Seattle",
				"AMC Pacific Place 11 - Seattle",
				"Regal Meridian 16 - Seattle",
				"Landmark Egyptian Theatre - Seattle",
				"Northwest Film Forum - Seattle",
				"Central Cinema - Seattle",
				"Big Picture - Seattle",
				"Cinerama Theatre - Seattle",
				"Pacific Science Center IMAX - Boeing Theatre - Seattle",
				"Pacific Science Center IMAX - PACCAR IMAX Theater - Seattle",
				"SIFF Cinema at the Uptown - Seattle",
				"SIFF Cinema at the Film Center - Seattle",
				"Landmark Harvard Exit - Seattle",
				"Admiral Twin - Seattle",
				"West Seattle Walk-In Theater - Seattle",
				"Lincoln Square Cinemas - Bellevue",
				"Landmark Seven Gables Theatre - Seattle",
				"Landmark Varsity Theatre - Seattle",
				"Grand Illusion Cinema - Seattle",
				"Sundance Cinemas Seattle - Seattle",
				"Landmark Guild 45th - Seattle",
				"Fremont Friday Nite Outdoor Movies - Seattle",
				"Majestic Bay Theatres - Seattle",
				"AMC Loews Factoria 8 - Bellevue",
				"Kirkland Parkplace Cinema - Kirkland",
				"Regal Crossroads Stadium 8 - Bellevue",
				"Lynwood Theatre - Bainbridge Island",
				"Bainbridge Cinemas at the Pavilion - Bainbridge Island",
				"Regal Cinemas Thornton Place Stadium 14 & IMAX - Seattle",
				"Totem Lake Cinemas - Kirkland",
				"Regal Bella Bottega 11 - Redmond",
				"Big Picture Redmond - Redmond",
				"iPic Theaters at Redmond Town Center - Redmond",
				"AMC Loews Oak Tree 6 - Seattle",
				"Regal East Valley 13 - Renton",
				"Regal Cinemas The Landing Stadium 14 - Renton",
				"Regal Issaquah 9 Theatres - Issaquah",
				"Tin Theater - Burien",
				"Landmark Crest Cinema Centre - Seattle",
				"Regal Parkway Plaza 12 - Tukwila",
				"AMC Southcenter 16 - Tukwila",
				"Rodeo Drive-In - Port Orchard",
				"Olympic Cinemas - Bremerton - Bremerton",
				"Regal South Sound Cinema 10 - Port Orchard",
				"Dragonfly Cinema - Port Orchard",
				"AMC Loews Woodinville 12 - Woodinville",
				"Cinebarre Mountlake Terrace - Mountlake Terrace",
				"Des Moines Cinema - Des Moines",
				"Edmonds Theater - Edmonds",
				"AMC Kitsap 8 - Silverdale",
				"Regal Silverdale Cinemas 4 - Silverdale",
				"Regal Poulsbo Cinema 10 - Poulsbo",
				"AMC Kent Station 14 - Kent"
			)
		);
		
		return Arrays.asList(new Object[][] {
			{ "98104", 5, SeattleTheaters }
		});
	}
	
	
	/**
	 * This creates and object for the web site and passes the 
	 * Selenium object to it.
	 * 
	 * It then opens the MovieTickets.com home page
	 */
	@Before
	public void openHomePage() {
		movieTickets = new MovieTickets(selenium);
		homePage = movieTickets.HomePage().open();	
	}
		
	
	/**
	 * This test looks up theaters by zip code and verifies 
	 * number of ticketing theaters & the list of theaters near you
	 */
	@Test
	public void test() {
		System.out.println(zipCode);
		System.out.println(expectedNumberOfTicketingTheaters);
		for(String theater : expectedListOfTheaters) {
			System.out.println(theater);
		}
		
		TheatersNearYouPage theatersPage = homePage.searchByZipCode(zipCode);
		selenium.waitForPageToLoad(TIMEOUT);
		
		// verify that the page title matches
		String actualTitle = selenium.getTitle();
		System.out.println(actualTitle);
		assertEquals(TheatersNearYouPage.Expected.title, actualTitle);
		
		// verify that the expected number of theaters matches
		String ticketingTheaters = selenium.getText(theatersPage.numberOfTicketingTheaters);
		System.out.println(ticketingTheaters);
		int start = "MAP".length(); // the word "MAP" is at the start of the string
		System.out.println(start);
		int end = ticketingTheaters.indexOf("ticketing theater"); // the number is after "ticketing theater"
		System.out.println(end);
		System.out.println(ticketingTheaters.substring(start, end));
		int actualNumberOfTicketingTheaters = Integer.valueOf(ticketingTheaters.substring(start, end).trim()); // parse the string for integer value
		System.out.println(actualNumberOfTicketingTheaters);
		assertEquals(expectedNumberOfTicketingTheaters, actualNumberOfTicketingTheaters);
		
		// verify that each expected theater is displayed on the page 
		for (int i=1; i<=expectedListOfTheaters.size(); i++) {
			String theater = theatersPage.getTheater(i);
			System.out.println(theater);
			assertTrue(expectedListOfTheaters.contains(theater));
		}
	}
	
	
	/**
	 * This just cleans up after Selenium.
	 */
	@After
	public void tearDown() {
		selenium.close();
		//selenium.stop();
	}
}
