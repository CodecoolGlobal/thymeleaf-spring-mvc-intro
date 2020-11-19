package com.raczkowski.springintro.backdoor.util;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;

@Component
public class DateComparator implements Comparator<Date> {

    @Override
    public int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }
}
