package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTests {
    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10.0, spiderMan.calculateTicketPrice(showing));
//        assertThrows(IllegalArgumentException.class, () -> spiderMan.calculateTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }

    //Case: First movie of the day discount > Special movie discount
    @Test
    void specialMovieFirstShowingOfTheDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
        System.out.println(Duration.ofMinutes(90));
    }

    //Case: Second movie of the day discount > Special movie discount
    @Test
    void specialMovieSecondShowingOfTheDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),9.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
        System.out.println(Duration.ofMinutes(90));
    }

    //Case: First movie of the day discount < Special movie discount
    @Test
    void specialMovieDiscountFirstShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(11.0, spiderMan.calculateTicketPrice(showing));
        System.out.println(Duration.ofMinutes(90));
    }

    //Case: Second movie of the day discount < Special movie discount
    @Test
    void specialMovieDiscountSecondShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(11.2, spiderMan.calculateTicketPrice(showing));
        System.out.println(Duration.ofMinutes(90));
    }

    //Case: No discount
    @Test
    void noDiscountShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(14.0, spiderMan.calculateTicketPrice(showing));
        System.out.println(Duration.ofMinutes(90));
    }

    @Test
    public void equalsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);
        Movie spiderMan2 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);

        assertEquals(spiderMan1, spiderMan2);
        assertEquals(spiderMan1, spiderMan1);
        assertTrue(spiderMan1.equals(spiderMan1));
        assertEquals(spiderMan1.hashCode(), spiderMan2.hashCode());
    }

    @Test
    public void notEqualsTest() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Movie turningRed = new Movie("Turning Red","a 2022 American computer-animated fantasy comedy film produced by Pixar Animation Studios", Duration.ofMinutes(85), 11, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));

        assertNotEquals(spiderMan, turningRed);
        assertNotEquals(spiderMan.hashCode(), turningRed.hashCode());
        assertNotEquals(spiderMan, showing);
        assertFalse(spiderMan.equals(null));
    }

    @Test
    public void specialCodeNotEqualsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Movie spiderMan2 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);
        assertNotEquals(spiderMan1, spiderMan2);
    }

    @Test
    public void ticketPriceNotEqualsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Movie spiderMan2 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),13.0, 1);
        assertNotEquals(spiderMan1, spiderMan2);
    }

    @Test
    public void durationNotEqualsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Movie spiderMan2 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(95),14.0, 1);
        assertNotEquals(spiderMan1, spiderMan2);
    }

    @Test
    public void descriptionNotEqualsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Movie spiderMan2 = new Movie("Spider-Man: No Way Home", "a 2022 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        assertNotEquals(spiderMan1, spiderMan2);
    }

    @Test
    public void titleNotEqualsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Movie spiderMan2 = new Movie("Spider-Man: Into the Spiderverse", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        assertNotEquals(spiderMan1, spiderMan2);
    }
}
