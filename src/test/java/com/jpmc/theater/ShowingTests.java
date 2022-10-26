package com.jpmc.theater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShowingTests {

    private Showing showing;

    @BeforeEach
    void setUp() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90), 14.0, 1);
        LocalDateTime time = LocalDateTime.of(2022, 10,6, 10, 0);
        showing = new Showing(spiderMan, 1, time);
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