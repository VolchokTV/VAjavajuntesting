package com.gridnine.testing.sort;

import com.gridnine.testing.Flight;

import java.util.ArrayList;
import java.util.List;

 public class SorterOfRules {

        private Sorter[] sorters = new Sorter[RulesToDelete.values().length];

    public SorterOfRules() {
            reset();
        }

        /**
         * @param filterType FilterType to get
         * @return Filter instance
         */
        Sorter get(RulesToDelete filterType) {
            return sorters[filterType.ordinal()];
        }

        /**
         * Adding new filters over existing ones
         *
         * @param newFilters Vararg of filters
         */
        void add(final Sorter... newFilters) {
            for (Sorter filter : newFilters) {
                sorters[filter.getType().ordinal()] = filter; // replace old filters of the same type
            }
        }

        /**
         * Removing every old filter and adding new ones
         *
         * @param newFilters Vararg of filters
         */
        public void replace(final Sorter... newFilters) {
            reset();
            add(newFilters);
        }

        /**
         * Removes filter of specified FilterType
         *
         * @param filterType FilterType to remove
         */
        void remove(@SuppressWarnings("SameParameterValue") RulesToDelete filterType) {
            sorters[filterType.ordinal()] = null;
        }

        /**
         * Prunes all filters from FilterBuilder instance
         */
        void reset() {
            sorters = new Sorter[RulesToDelete.values().length];
        }

        /**
         * @param unfilteredFlights List of flights needed to be filtered
         * @return List with filtered flights
         */
        public List<Flight> filter(List<Flight> unfilteredFlights) {
            List<Flight> filteredFlights = new ArrayList<>();
            for (Flight flight : unfilteredFlights) {
                boolean isFlightPassing = true;
                for (Sorter filter : sorters) {
                    if (filter != null) {
                        if (!filter.approved(flight)) {
                            isFlightPassing = false;
                            break;
                        }
                    }
                }
                if (isFlightPassing) {
                    filteredFlights.add(flight);
                }
            }
            return filteredFlights;
        }
    }


