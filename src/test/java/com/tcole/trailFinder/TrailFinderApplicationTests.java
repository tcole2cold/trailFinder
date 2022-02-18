package com.tcole.trailFinder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class TrailFinderApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void randomnessBenchmark() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i<22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 5, 8);
			Route anotherTestRoute = scout.generateARandomRoute(i, 5, 8);
			Assertions.assertNotEquals(testRoute.getTotalDistance(), anotherTestRoute.getTotalDistance());
		}
	}

	@Test
	void singleRequestForPerformanceBenchmark() throws IOException {
		RouteScout scout = new RouteScout();
		Route testRoute = scout.generateARandomRoute(5, 2, 5);
	}

	@Test
	void routeGenerationTwoToThree() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 2, 3);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationThreeToFour() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 3, 4);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationFourToFive() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 4, 5);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationFiveToSix() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 5, 6);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationSixToSeven() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 6, 7);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationSevenToEight() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 7, 8);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationEightToNine() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 8, 9);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}

	@Test
	void routeGenerationNineToTen() throws IOException {
		RouteScout scout = new RouteScout();
		for (int i = 0; i < 22; i++) {
			Route testRoute = scout.generateARandomRoute(i, 9, 10);
			Assertions.assertNotNull(testRoute, "Failed to generate requested route for intersection " + String.valueOf(i));
		}
	}
}