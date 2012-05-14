package com.movietickets.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.movietickets.selenium.SeleniumTestBase;
import com.movietickets.selenium.pages.HomePage;
import com.movietickets.selenium.site.MovieTickets;


/**
 * This test exercises the top 10 movies list.
 * 
 * @author Aaron Evans <aarone@one-shore.com>
 */
@RunWith(Parameterized.class)
public class TopMovies extends SeleniumTestBase {

	MovieTickets movieTickets;
	HomePage page;

	private int rank;
	private String expectedTitle;
	
	/**
	 * Constructor
	 * 
	 * This loads the parameterized data
	 * 
	 * @param rank
	 * @param title
	 */
	public TopMovies(int rank, String title) {
		this.rank = rank;
		this.expectedTitle = title;
	}
	
	
	/**
	 * This is the current list of top 10 movies.
	 * It is used to compare to the results on the website.
	 * 
	 * Note that this list can get out of date if not updated.
	 * It can also be moved to a spreadsheet which could be
	 * populated by a script like printTop10Movies()
	 *  
	 * @return Collection<Object[]>
	 */
	@Parameters
	public static Collection<Object[]> getTop10Movies() {
		return Arrays.asList(new Object[][] {
				{1, "The Avengers"},
				{2, "Battleship"},
				{3, "Dark Shadows"},
				{4, "The Pirates! Band of Misfits"},
				{5, "Think Like a Man"},
				{6, "The Lucky One"},
				{7, "The Hunger Games"},
				{8, "What to Expect When You're Expecting"},
				{9, "The Five-Year Engagement"},
				{10, "Safe"}
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
		page = movieTickets.HomePage().open();	
	}
	
	
	/**
	 * This test just prints the top 10 list.
	 * 
	 * It is an example of how you could populate the top 10 list
	 * used by the other data driven tests.
	 * 
	 * @throws Exception
	 */
	@Test
	public void printTopTenMovies() throws Exception {
		for(int i=1; i<=10; i++) {
			String actualTitle = selenium.getText(page.getTopMovie(i));
			System.out.println(i + " : " + actualTitle);
		}
	}
	
	
	/** 
	 * This test will verify that the top 10  list has the same  
	 * titles as expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void verifyTop10Titles() throws Exception {
		String actualTitle = selenium.getText(page.getTopMovie(rank));
		System.out.println(rank + " : " + "expected: " + expectedTitle + " actual: " + actualTitle);
		assertEquals(expectedTitle, actualTitle);
	}
	
	
	/**
	 * This test will open each movie in the top 10 list and 
	 * verify that the page title matches the expected movie.
	 *  
	 * @throws Exception
	 */
	@Test
	public void openTopTenMovies() throws Exception {
		selenium.click(page.getTopMovie(rank));
		selenium.waitForPageToLoad(TIMEOUT);

		System.out.println(selenium.getTitle());
		assertTrue(selenium.getTitle().contains(expectedTitle));
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
