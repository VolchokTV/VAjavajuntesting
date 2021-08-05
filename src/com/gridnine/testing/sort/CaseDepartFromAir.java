package com.gridnine.testing.sort;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

/**
 * Отсекаем перелеты где имеются сегменты с ВЫЛЕТом раньше посадки
 */

public class CaseDepartFromAir implements Sorter{
    public boolean approved(Flight flight) {
    for (Segment segment : flight.getSegments()) {
        if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
            return false;
        }
    }
    return true;
}




    public RulesToDelete getType() {
        return RulesToDelete.DEPART_FROM_AIR;
    }
}