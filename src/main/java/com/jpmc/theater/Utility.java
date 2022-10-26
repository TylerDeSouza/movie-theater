package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.jpmc.theater.DiscountConstants.MOVIE_CODE_SPECIAL;
import static com.jpmc.theater.DiscountConstants.SEVENTH_DAY_OF_MONTH;

public class Utility {
    private static LocalDateProvider provider = LocalDateProvider.singleton();

    static boolean isEligibleForTimeDiscount(LocalDateTime showStartTime) {
        return showStartTime.isAfter(LocalDateTime.of(provider.currentDate(), LocalTime.of(10, 59, 59, 59)))
                && showStartTime.isBefore(LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 0)));
    }

    static boolean isEligibleForDayDiscount(LocalDateTime showStartTime) {
        return showStartTime.getDayOfMonth() == SEVENTH_DAY_OF_MONTH;
    }

    static boolean isEligibleForSpecialCodeDiscount(int specialCode) {
        return specialCode == MOVIE_CODE_SPECIAL;
    }
}
