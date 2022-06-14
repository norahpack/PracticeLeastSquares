package com.example.practiceleastsquares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonData {

    List<Double> moods;
    List<String> habits;
    List<List<Double>> habitTracker;
    List<Date> dates;

    public PersonData(List<String> habits){
        this.habits=habits;
        this.habitTracker=new ArrayList<List<Double>>();
        this.moods = new ArrayList<Double>();
        this.dates = new ArrayList<Date>();
    }


    public void addData(Double mood, List<Double> dayHabits, Date date){
        this.moods.add(mood);
        this.dates.add(date);
        this.habitTracker.add(dayHabits);
    }

}
