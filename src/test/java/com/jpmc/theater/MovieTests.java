package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieTests {

    @Mock
    private LocalDateProvider provider;

    @Spy
    Movie movie;


    //Case: Show time in the range of 11am-4pm
//    @Test
    void isEligibleForTimeDiscount() {
        LocalDateTime time = LocalDateTime.of(2022, 10,7, 11, 0);
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),13.0, 0);
        Showing showing = new Showing(spiderMan, 5, time);
        assertEquals(9.75, spiderMan.calculateTicketPrice(showing));
    }

    //Case: Show day is the 7th day of the month
//    @Test
    void isEligibleForSeventhDayDiscount() {
        LocalDateTime time = LocalDateTime.of(2022, 10,7, 9, 0);
        when(provider.currentDate()).thenReturn(LocalDate.from(time));
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),13.0, 0);
        Showing showing = new Showing(spiderMan, 5, time);
        assertEquals(12.0, spiderMan.calculateTicketPrice(showing));
    }

    //Case: First movie of the day discount > Special movie discount
    @Test
    void specialMovieFirstShowingOfTheDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    //Case: Second movie of the day discount > Special movie discount
    @Test
    void specialMovieSecondShowingOfTheDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),9.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(7.6, spiderMan.calculateTicketPrice(showing));
    }

    //Case: First movie of the day discount < Special movie discount
    @Test
    void specialMovieDiscountFirstShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(11.0, spiderMan.calculateTicketPrice(showing));
    }

    //Case: Second movie of the day discount < Special movie discount
    @Test
    void specialMovieDiscountSecondShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(11.2, spiderMan.calculateTicketPrice(showing));
    }

    //Case: No discount
//    @Test
    void noDiscountShowing() {
        LocalDateTime time = LocalDateTime.of(2022, 10,7, 9, 0);
        when(provider.currentDate()).thenReturn(LocalDate.from(time));
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);
        Showing showing = new Showing(spiderMan, 3, time);
        assertEquals(14.0, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    public void equalsTest() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);
        Movie spiderMan2 = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 0);

        assertEquals(spiderMan1, spiderMan2);
        assertEquals(spiderMan1, spiderMan1);
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
