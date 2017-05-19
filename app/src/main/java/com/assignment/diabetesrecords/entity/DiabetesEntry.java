package com.assignment.diabetesrecords.entity;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class DiabetesEntry {

private int DiabetesEntryId;
    private String EntryDate;
    private String EntryTime;
    private String FoodTakenStatus;
    private float GlucoseReading;
    private String Notes;

    public DiabetesEntry() {
    }

    public DiabetesEntry(int diabetesEntryId, String entryDate, String entryTime, String foodTakenStatus, float glucoseReading, String notes) {
        DiabetesEntryId = diabetesEntryId;
        EntryDate = entryDate;
        EntryTime = entryTime;
        FoodTakenStatus = foodTakenStatus;
        GlucoseReading = glucoseReading;
        Notes = notes;
    }

    public int getDiabetesEntryId() {
        return DiabetesEntryId;
    }

    public void setDiabetesEntryId(int diabetesEntryId) {
        DiabetesEntryId = diabetesEntryId;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(String entryTime) {
        EntryTime = entryTime;
    }

    public String getFoodTakenStatus() {
        return FoodTakenStatus;
    }

    public void setFoodTakenStatus(String foodTakenStatus) {
        FoodTakenStatus = foodTakenStatus;
    }

    public float getGlucoseReading() {
        return GlucoseReading;
    }

    public void setGlucoseReading(float glucoseReading) {
        GlucoseReading = glucoseReading;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
