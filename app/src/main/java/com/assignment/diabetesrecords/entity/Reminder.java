package com.assignment.diabetesrecords.entity;

/**
 * Created by Viswesvar on 12/8/16.
 */
public class Reminder {

    private int id;
    private String title, description, repeation_frequency, reminder_date, reminder_time;

    public Reminder(int id, String title, String description, String repeation_frequency, String reminder_date, String reminder_time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.repeation_frequency = repeation_frequency;
        this.reminder_date = reminder_date;
        this.reminder_time = reminder_time;
    }


    public Reminder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepeation_frequency() {
        return repeation_frequency;
    }

    public void setRepeation_frequency(String repeation_frequency) {
        this.repeation_frequency = repeation_frequency;
    }

    public String getReminder_date() {
        return reminder_date;
    }

    public void setReminder_date(String reminder_date) {
        this.reminder_date = reminder_date;
    }

    public String getReminder_time() {
        return reminder_time;
    }

    public void setReminder_time(String reminder_time) {
        this.reminder_time = reminder_time;
    }
}
