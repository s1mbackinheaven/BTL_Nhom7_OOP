package com.example.BTL_Nhom7_OOP.dto.request;

import java.time.LocalDateTime;

public class AppointmentRequest {
    private String ownerName;
    private String petName;
    private Integer petAge;

    private LocalDateTime appointmentDateTime;

    private String nickname = ""; // Không bắt buộc
    private String note = ""; // Không bắt buộc
    private Integer preferredDoctorId; // ID bác sĩ ưu tiên (có thể null nếu khách không chọn)

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

    public Integer getPreferredDoctorId() {
        return preferredDoctorId;
    }

    public void setPreferredDoctorId(Integer preferredDoctorId) {
        this.preferredDoctorId = preferredDoctorId;
    }
}
