package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> airtravels = FlightBuilder.createFlights();
        Filter filterUpcoming = new FilterDepartureByTime(LocalDateTime.now());
        Filter filterDepartureBeforeArrival = new FilterDepartureBeforeArrival();
        Filter filterLessTwoHoursTotalGroundTime = new FilterTotalGroundTime(7200);

        FilterBuilder filterBuilder = new FilterBuilder();

        // Перелеты с верной начальной датой
        System.out.println("\nUpcoming flights:");

        filterBuilder.replace(filterUpcoming);
        List<Flight> flightsUpcoming = filterBuilder.filter(flights);

        for (Flight flight : flightsUpcoming) {
            System.out.println(flight);
        }

    }
}
