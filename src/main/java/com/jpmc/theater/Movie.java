package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL1 = 1;
    private static int MOVIE_CODE_SPECIAL2 = 2;

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
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL1 == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        if(showStartTime.isAfter(LocalDateTime.of(provider.currentDate(), LocalTime.of(10, 59, 59, 59))) && showStartTime.isBefore(LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 0)))) {
            specialDiscount = ticketPrice * 0.25;  // 25% discount for movie between 11am-4pm
        }

        double dayDiscount = 0;
        if(provider.currentDate().getDayOfMonth() == 7) {
            dayDiscount = 1;
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }

        // biggest discount wins
        return Math.max(dayDiscount, Math.max(specialDiscount, sequenceDiscount));
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