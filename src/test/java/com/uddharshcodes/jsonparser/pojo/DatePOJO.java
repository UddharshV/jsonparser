package com.uddharshcodes.jsonparser.pojo;

import java.time.*;

public class DatePOJO {
    private LocalDate date;
    private String day;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
