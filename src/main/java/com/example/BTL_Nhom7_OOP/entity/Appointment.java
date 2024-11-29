package com.example.BTL_Nhom7_OOP.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ownerName;
    private String petName;
    private Integer petAge;

//    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime appointmentDateTime;

    private String nickname;
    private String note;
    private String status;

    @ManyToOne
    @JoinColumn(name = "doctor", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "preferred_doctor", nullable = true)  // Đây là bác sĩ ưu tiên nếu có
    private Doctor preferredDoctor;

    public Appointment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getPreferredDoctor() {
        return preferredDoctor;
    }

    public void setPreferredDoctor(Doctor preferredDoctor) {
        this.preferredDoctor = preferredDoctor;
    }
}
