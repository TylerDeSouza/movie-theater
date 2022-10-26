package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

import static com.jpmc.theater.DiscountConstants.*;
import static com.jpmc.theater.Utility.isEligibleForDayDiscount;
import static com.jpmc.theater.Utility.isEligibleForSpecialCodeDiscount;

public class Movie {

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) {
        double discount = 0;

        if (isEligibleForSpecialCodeDiscount(specialCode)) {
            discount = Math.max(discount, ticketPrice * TWENTY_PERCENT_DISCOUNT);  // 20% discount for special movie
        }

        if(Utility.isEligibleForTimeDiscount(showing.getStartTime())) {
            discount = Math.max(discount, ticketPrice * TWENTY_FIVE_PERCENT_DISCOUNT);  // 25% discount for movie between 11am-4pm
        }

        if(isEligibleForDayDiscount(showing.getStartTime())) {
            discount = Math.max(discount, SEVENTH_DAY_OF_MONTH_DISCOUNT);
        }

        if (showing.isSequence(SEQUENCE_ONE)) {
            discount = Math.max(discount, SEQUENCE_ONE_DISCOUNT); // $3 discount for 1st show
        } else if (showing.isSequence(SEQUENCE_TWO)) {
            discount = Math.max(discount, SEQUENCE_TWO_DISCOUNT); // $2 discount for 2nd show
        }

        return Math.round(discount * 100.0)/100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}