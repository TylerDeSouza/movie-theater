package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTests {

    @Test
    void totalFee() {
        Customer customer = new Customer("John Doe", "unused-id");
        LocalDateTime time = LocalDateTime.of(2022, 10,6, 10, 0);
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home","a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90), 12.5, 1),
                1,
                time
        );
        assertEquals(37.5, new Reservation(customer, showing, 3).totalFee());
    }
}
