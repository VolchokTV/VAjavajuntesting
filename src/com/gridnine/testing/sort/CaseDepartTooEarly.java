package com.gridnine.testing.sort;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;

public class CaseDepartTooEarly implements Sorter {
    private final LocalDateTime dateTime;

    public CaseDepartTooEarly(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean approved(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isBefore(dateTime)) {
                return false;
            }
        }
        return true;
    }

    public RulesToDelete getType() {
        return RulesToDelete.DEPART_TOO_EARLY;
    }
}
