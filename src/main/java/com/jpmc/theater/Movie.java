package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;
    private LocalDateProvider provider;

    public Movie(String title, String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
        this.provider = LocalDateProvider.singleton();
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
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
    }

    private double getDiscount(int showSequence, LocalDateTime showStartTime) {
        double discount = 0;
        if (specialCode == MOVIE_CODE_SPECIAL) {
            discount = Math.max(discount, ticketPrice * 0.2);  // 20% discount for special movie
        }

        if(isEligibleForTimeDiscount(showStartTime)) {
            discount = Math.max(discount, ticketPrice * 0.25);  // 25% discount for movie between 11am-4pm
        }

        if(provider.currentDate().getDayOfMonth() == 7) {
            discount = Math.max(discount, 1);
        }

        if (showSequence == 1) {
            discount = Math.max(discount, 3); // $3 discount for 1st show
        } else if (showSequence == 2) {
            discount = Math.max(discount, 2); // $2 discount for 2nd show
        }

        return discount;
    }

    private boolean isEligibleForTimeDiscount(LocalDateTime showStartTime) {
        return showStartTime.isAfter(LocalDateTime.of(provider.currentDate(), LocalTime.of(10, 59, 59, 59)))
                && showStartTime.isBefore(LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 0)));
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