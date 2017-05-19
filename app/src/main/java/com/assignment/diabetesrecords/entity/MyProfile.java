package com.assignment.diabetesrecords.entity;

/**
 * Created by Viswesvar on 12/5/16.
 */
public class MyProfile {

    private int Id;
    private String FirstName, LastName, MiddleName, DOB, DiabetesType, Gender;

    public MyProfile() {
    }

    public MyProfile(int id, String firstName, String lastName, String middleName, String DOB, String diabetesType, String gender) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        MiddleName = middleName;
        this.DOB = DOB;
        DiabetesType = diabetesType;
        Gender = gender;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDiabetesType() {
        return DiabetesType;
    }

    public void setDiabetesType(String diabetesType) {
        DiabetesType = diabetesType;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
