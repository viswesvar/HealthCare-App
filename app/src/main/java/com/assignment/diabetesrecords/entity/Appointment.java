package com.assignment.diabetesrecords.entity;

/**
 * Created by Viswesvar 12/5/16.
 */
public class Appointment {

    private int Id;
    private int DoctorId;
    private String DoctorName;
    private String DoctorPhone;
    private String DoctorEmail;
    private String AppointmentDate, AppointmentTime, AppointmentReason;


    public Appointment() {
    }


    public Appointment(int id, int doctorId, String doctorName, String doctorPhone, String doctorEmail, String appointmentDate, String appointmentTime, String appointmentReason) {
        Id = id;
        DoctorId = doctorId;
        DoctorName = doctorName;
        DoctorPhone = doctorPhone;
        DoctorEmail = doctorEmail;
        AppointmentDate = appointmentDate;
        AppointmentTime = appointmentTime;
        AppointmentReason = appointmentReason;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(int doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorPhone() {
        return DoctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        DoctorPhone = doctorPhone;
    }

    public String getDoctorEmail() {
        return DoctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        DoctorEmail = doctorEmail;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public String getAppointmentReason() {
        return AppointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        AppointmentReason = appointmentReason;
    }
}
