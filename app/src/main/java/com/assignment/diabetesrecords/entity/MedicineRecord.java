package com.assignment.diabetesrecords.entity;


public class MedicineRecord {

private int Id;
    private String EntryDate;
    private String EntryTime;
    private String FoodTakenStatus;

    private String Title;
    private String Description;
    private String InsulineInformation;


    public MedicineRecord() {
    }


    public MedicineRecord(int id, String entryDate, String entryTime, String foodTakenStatus, String title, String description, String insulineInformation) {
        Id = id;
        EntryDate = entryDate;
        EntryTime = entryTime;
        FoodTakenStatus = foodTakenStatus;
        Title = title;
        Description = description;
        InsulineInformation = insulineInformation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getInsulineInformation() {
        return InsulineInformation;
    }

    public void setInsulineInformation(String insulineInformation) {
        InsulineInformation = insulineInformation;
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


}
