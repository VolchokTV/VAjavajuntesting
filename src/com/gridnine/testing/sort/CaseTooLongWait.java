package com.gridnine.testing.sort;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 *  Удаляем перелеты где присутствует долгое ожидание следующего вылета
 */

public class CaseTooLongWait implements Sorter{

   // лимит ожидания вылета в секундах
    private final long timeToFlyOut;

    public CaseTooLongWait(long timeToFlyOut) {
        this.timeToFlyOut = timeToFlyOut;
    }

    public boolean approved(Flight flight) {
        long groundTime = 0;

        List<Segment> segments = flight.getSegments();
        int segmentsSize = segments.size();

        if (segmentsSize >= 2) {
            for (int i = 1; i < segmentsSize; i++) {
                LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
                LocalDateTime currentDeparture = segments.get(i).getDepartureDate();

                long segmentGroundTime = previousArrival.until(currentDeparture, ChronoUnit.SECONDS);

                groundTime += segmentGroundTime;
            }
        }

        return groundTime <= this.timeToFlyOut;
    }

    public RulesToDelete getType() {
        return RulesToDelete.TOO_LONG_WAIT;
    }
}

