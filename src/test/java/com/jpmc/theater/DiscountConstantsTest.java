package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static com.jpmc.theater.DiscountConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountConstantsTest {

    @Test
    void checkEquality() {
        assertEquals(MOVIE_CODE_SPECIAL, 1);
        assertEquals(SEQUENCE_ONE, 1);
        assertEquals(SEQUENCE_TWO, 2);
        assertEquals(SEVENTH_DAY_OF_MONTH, 7);

        assertEquals(TWENTY_PERCENT_DISCOUNT, 0.2);
        assertEquals(TWENTY_FIVE_PERCENT_DISCOUNT, 0.25);
        assertEquals(SEQUENCE_ONE_DISCOUNT, 3);
        assertEquals(SEQUENCE_TWO_DISCOUNT, 2);
        assertEquals(SEVENTH_DAY_OF_MONTH_DISCOUNT, 1);
    }
}