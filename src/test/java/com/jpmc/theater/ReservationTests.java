package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home","a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }
}
