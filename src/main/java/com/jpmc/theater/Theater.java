package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Theater {

    LocalDateProvider provider;
    private final List<Showing> schedule;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(Theater.class.getName());

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home","a 2021 American superhero film based on the Marvel Comics character Spider-Man", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red","a 2022 American computer-animated fantasy comedy film produced by Pixar Animation Studios", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman","a 2022 American superhero film based on the DC Comics character Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() throws JsonProcessingException {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        ObjectNode rootNode = objectMapper.createObjectNode();
        schedule.forEach(s ->
                {
                    System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee());

                    ObjectNode childNode = objectMapper.createObjectNode();
                    childNode.put("startTime", String.valueOf(s.getStartTime()));
                    childNode.put("movieTitle", s.getMovie().getTitle());
                    childNode.put("runningTime", String.valueOf(s.getMovie().getRunningTime()));
                    childNode.put("movieFee", s.getMovieFee());

                    rootNode.set(String.valueOf(s.getSequenceOfTheDay()), childNode);

                }
        );
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        System.out.println(jsonString);
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args)  {
        Theater theater = new Theater(LocalDateProvider.singleton());
        try {
            theater.printSchedule();
        } catch (JsonProcessingException ex) {
            LOGGER.log(Level.SEVERE, "JsonProcessingException occurred in printSchedule", ex);
        }
    }
}
