package com.neostudy.calculatorvacationpay.model.calendar;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Set;


public class CalenderOfWorkingDays {

    public final static Double AVERAGE_MONTHLY_NUMBER_OF_DAYS = 29.3;

    public static Integer getQuantityVacationDays(LocalDate startDate, LocalDate endDate) {

        HolidayDates holidayDates = new HolidayDates();

        int quantityVacationDays = getQuantityWorkingDays(startDate, endDate);

        LocalDate date = startDate;
        endDate = endDate.plusDays(1);
        while (date.isBefore(endDate)) {

            Set<MonthDay> holidays = holidayDates.getHolidays(Year.of(date.getYear()));
            for (MonthDay holiday : holidays) {
                if (holiday.compareTo(holidayDates.LocalDateInMonthDay(date)) == 0) {
                    quantityVacationDays = quantityVacationDays - 1;
                }
            }
            date = date.plusDays(1);
        }

        return quantityVacationDays;
    }

    private static Integer getQuantityWorkingDays(LocalDate startDate, LocalDate endDate) {
        return (int) startDate.until(endDate, ChronoUnit.DAYS) + 1;
    }

}
