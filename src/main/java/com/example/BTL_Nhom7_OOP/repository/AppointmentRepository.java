package com.example.BTL_Nhom7_OOP.repository;

import com.example.BTL_Nhom7_OOP.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByOwnerName(String ownerName);
    List<Appointment> findAllByAppointmentDateTime(LocalDateTime appointmentDateTime);
    List<Appointment> findAllByStatusAndPreferredDoctorIdOrPreferredDoctorIsNull(String status, Long doctorId);

    List<Appointment> findAllByStatus(String message);
}
