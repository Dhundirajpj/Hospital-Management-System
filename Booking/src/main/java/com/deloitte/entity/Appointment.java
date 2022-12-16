package com.deloitte.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment extends BaseEntity<String> {

    private String hospitalId;
    private String PatientId;
    private LocalDate date;
    private LocalTime time;
    private String roomId;

    public String gethospitalId() {
        return hospitalId;
    }

    public void sethospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Appointment(String id, String name, String hospitalId, String PatientId, LocalDate date, LocalTime time, String roomId) {
        super(id, name);
        this.hospitalId = hospitalId;
        this.PatientId = PatientId;
        this.date = date;
        this.time = time;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "hospitalId='" + hospitalId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", roomId='" + roomId + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
