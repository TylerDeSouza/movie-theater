package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {

    private Theater theater;
    private Customer john;

    @BeforeEach
    void setUp() {
        theater = new Theater(LocalDateProvider.singleton());
        john = new Customer("John Doe", "id-12345");
    }

    @Test
    void totalFeeForCustomer() {
        Reservation reservation = theater.reserve(john, 2, 4);
        System.out.println("You have to pay " + reservation.totalFee());
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printMovieSchedule() throws JsonProcessingException {
        theater.printSchedule();
    }

    @Test
    void throwIllegalStateException() {
        assertThrows(IllegalStateException.class, () -> theater.reserve(john, 10, 4));
    }

}
