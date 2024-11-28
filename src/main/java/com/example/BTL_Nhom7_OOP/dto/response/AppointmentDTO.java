package com.example.BTL_Nhom7_OOP.dto.response;

import com.example.BTL_Nhom7_OOP.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private int id;

    private String ownerName;
    private String petName;
    private Integer petAge;

//    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime appointmentDateTime;

    private String nickname;
    private String note;
    private String status;
    private DoctorDTO preferredDoctor;

    public AppointmentDTO(int id, String ownerName, String petName, Integer petAge, LocalDateTime appointmentDateTime, String nickname, String note, String status, DoctorDTO preferredDoctor) {
        this.id = id;
        this.ownerName = ownerName;
        this.petName = petName;
        this.petAge = petAge;
        this.appointmentDateTime = appointmentDateTime;
        this.nickname = nickname;
        this.note = note;
        this.status = status;
        this.preferredDoctor = preferredDoctor;
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

    public DoctorDTO getPreferredDoctor() {
        return preferredDoctor;
    }

    public void setPreferredDoctor(DoctorDTO preferredDoctor) {
        this.preferredDoctor = preferredDoctor;
    }

    public static AppointmentDTO fromEntity(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getOwnerName(),
                appointment.getPetName(),
                appointment.getPetAge(),
                appointment.getAppointmentDateTime(),
                appointment.getNickname(),
                appointment.getNote(),
                appointment.getStatus(),
//                DoctorDTO.fromEntity(appointment.getPreferredDoctor())
                appointment.getPreferredDoctor() != null ? DoctorDTO.fromEntity(appointment.getPreferredDoctor()) : null // Kiểm tra null
        );
    }
}
