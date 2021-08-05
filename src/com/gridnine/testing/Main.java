package com.gridnine.testing;

import com.gridnine.testing.sort.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> airtravels = FlightBuilder.createFlights();
        Sorter sortByStartDate = new CaseDepartTooEarly(LocalDateTime.now());
        Sorter filterDepartureBeforeArrival = new CaseDepartFromAir();
        Sorter filterLessTwoHoursTotalGroundTime = new CaseTooLongWait(7200);

        SorterOfRules sorter = new SorterOfRules();

        // Перелеты с верной начальной датой
        System.out.println("\nUpcoming flights:");

        sorter.replace(sortByStartDate);
        List<Flight> flightsUpcoming = sorter.filter(airtravels);

        for (Flight flight : flightsUpcoming) {
            System.out.println(flight);
        }

    }
}
