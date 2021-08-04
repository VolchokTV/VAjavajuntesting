package com.gridnine.testing;

public interface Sorter {

    /**
     * Is used to check if Flight meets the filter requirement
     *
     * @param flight Flight instance needed to be checked
     * @return True if flight meets the filter requirement, false if otherwise
     */
    boolean approved(Flight flight);

    /**
     * Getting type of filter to manipulate within FilterBuilder
     *
     * @return FilterType enum value
     */
    RulesToDelete getType();
}
