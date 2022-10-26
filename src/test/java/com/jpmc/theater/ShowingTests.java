package com.jpmc.theater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ShowingTests {

    private Movie spiderMan;
    private Showing showing;

    @BeforeEach
    void setUp() {
        spiderMan = new Movie("Spider-Man: No Way Home","a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90),14.0, 1);
        showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    }

    @Test
    void isSequence() {
        assertTrue(showing.isSequence(1));
        assertFalse(showing.isSequence(2));
    }

    @Test
    void calculateFee() {
        assertEquals(14.0, showing.getMovieFee());
    }
}