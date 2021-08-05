package com.gridnine.testing;

import com.gridnine.testing.sort.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Flight> airtravels = FlightBuilder.createFlights();
        Sorter sortByStartDate = new CaseDepartTooEarly(LocalDateTime.now());
        Sorter sortByAirDepart = new CaseDepartFromAir();
        Sorter filterLessTwoHoursTotalGroundTime = new CaseTooLongWait(7200);

        SorterOfRules sorter = new SorterOfRules();

        // Перелеты с верной начальной датой
        System.out.println("\nСписок авиа-перелетов позднее текущего времени:");

        sorter.replace(sortByStartDate);
        List<Flight> flightsUpcoming = sorter.filter(airtravels);

        Stream streamFlightsUp = flightsUpcoming.stream();
        flightsUpcoming.stream().forEach(x -> System.out.println(x));

        // исключить перелеты с датой прилета позднее даты вылета
        System.out.println("\nСписок авиа-перелетов c прибытием раньше вылета следующего :");

        sorter.replace(sortByAirDepart);
        List<Flight> flightsWithDeletedAirDepart = sorter.filter(airtravels);

        Stream streamFlightsWithDeletedAirDepart = flightsWithDeletedAirDepart.stream();
        flightsWithDeletedAirDepart.stream().forEach(x -> System.out.println(x));

        // исключить: перелеты если общее время, проведённое на земле превышает два часа
        System.out.println("\nСписок авиа-перелетов c пересадкой меньше двух часов:");

        sorter.replace(filterLessTwoHoursTotalGroundTime);
        List<Flight> flightsLessTwoHoursWait = sorter.filter(airtravels);

        Stream stream = flightsLessTwoHoursWait.stream();
        flightsLessTwoHoursWait.stream().forEach(x -> System.out.println(x));

    }
}
