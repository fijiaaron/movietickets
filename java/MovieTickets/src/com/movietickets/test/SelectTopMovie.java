package com.movietickets.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.movietickets.selenium.SeleniumTestBase;
import com.movietickets.selenium.pages.HomePage;
import com.movietickets.selenium.site.MovieTickets;

public class SelectTopMovie extends SeleniumTestBase {

	MovieTickets movieTickets;
	
	@Before
	public void setUp() {
		movieTickets = new MovieTickets(selenium);
	}
	
	@Test
	public void test() throws Exception {
		HomePage page = movieTickets.HomePage().open();		
		assertEquals(HomePage.Expected.title, page.getTitle());
		
		for(int i=0; i<10; i++) {
			System.out.println(selenium.getText(page.getTopMovie(i)));
		}
	}
}
