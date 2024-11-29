package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.response.AppointmentDTO;
import com.example.BTL_Nhom7_OOP.dto.request.AppointmentRequest;
import com.example.BTL_Nhom7_OOP.entity.Appointment;
import com.example.BTL_Nhom7_OOP.entity.Doctor;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.service.AppointmentService;
import com.example.BTL_Nhom7_OOP.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    //đặt lịch hẹn
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setOwnerName(appointmentRequest.getOwnerName());
        appointment.setPetName(appointmentRequest.getPetName());
        appointment.setPetAge(appointmentRequest.getPetAge());
        appointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
        appointment.setNickname(appointmentRequest.getNickname());
        appointment.setNote(appointmentRequest.getNote());

        if (appointmentRequest.getPreferredDoctorId() != null) {
            Doctor preferredDoctor = doctorService.getDoctorById(appointmentRequest.getPreferredDoctorId());
            appointment.setPreferredDoctor(preferredDoctor);
        }

        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(AppointmentDTO.fromEntity(createdAppointment));
    }

    //lấy lịch hẹn qua id
    @GetMapping("/get_appointment_by_id/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentsById(@PathVariable int id) {
        Appointment appointment = appointmentService.getAppointment(id);
        return ResponseEntity.ok(AppointmentDTO.fromEntity(appointment));
    }

    @GetMapping("/get_all_appointment")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = AppointmentDTO.fromEntity(appointment);
            appointmentDTOS.add(appointmentDTO);
        }
        return ResponseEntity.ok(appointmentDTOS);
    }

    @GetMapping("/get_appointment_by_name/{name}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByName(@PathVariable String name) {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByName(name);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/get_appointment_by_date/{date}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDate(@PathVariable String date) {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDate(date);
        return ResponseEntity.ok(appointments);
    }

    //check in
    @PutMapping("/check_in/{id}")
    public ResponseEntity<String> checkInAppointment(@PathVariable int id) {
        try {
            appointmentService.checkInAppointment(id);
            return ResponseEntity.ok("Khách hàng đã check-in thành công cho cuộc hẹn với ID: " + id);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //bác sĩ lấy hồ sơ
    @GetMapping("/get_appointment_for_doctor/{id}")
    public ResponseEntity<AppointmentDTO> getNextAppointmentForDoctor(@PathVariable int id) {
        try {
            Appointment appointment = appointmentService.getNextAppointmentForDoctor(id);
            AppointmentDTO appointmentDTO = AppointmentDTO.fromEntity(appointment);
            return ResponseEntity.ok(appointmentDTO);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Bác sĩ hoàn thành hồ sơ
    @PutMapping("/commpleted_appointment/{id}")
    public ResponseEntity<String> completeAppointment(@PathVariable int id) {
        try {
            appointmentService.completeAppointment(id);
            return ResponseEntity.ok("Hồ sơ đã được hoàn tất thành công.");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/get_all_appointment_commpleted")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsCommpleted() {
        List<Appointment> appointments = appointmentService.getAllAppointmentsCompleted();
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = AppointmentDTO.fromEntity(appointment);
            appointmentDTOS.add(appointmentDTO);
        }
        return ResponseEntity.ok(appointmentDTOS);
    }
}

