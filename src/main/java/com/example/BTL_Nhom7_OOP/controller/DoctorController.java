package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.DoctorDTO;
import com.example.BTL_Nhom7_OOP.dto.DoctorRequest;
import com.example.BTL_Nhom7_OOP.entity.Doctor;
import com.example.BTL_Nhom7_OOP.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setRoom(doctorRequest.getRoom());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setStatus(doctorRequest.getStatus());

        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity.ok(convertToDTO(createdDoctor));
    }

    @GetMapping("/get_doctor/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(convertToDTO(doctor));
    }

    @GetMapping("/get_all_doctor")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorDTOS.add(convertToDTO(doctor));
        }
        return ResponseEntity.ok(doctorDTOS);
    }

    @PutMapping("/update_infor_doctor/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable int id, @RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setRoom(doctorRequest.getRoom());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setStatus(doctorRequest.getStatus());
        Doctor newDoctor = doctorService.updateInforDoctor(id, doctor);
        return ResponseEntity.ok(convertToDTO(newDoctor));
    }

    @PutMapping("/set_status_doctor/{id}")
    public ResponseEntity<DoctorDTO> setDoctorStatus(@PathVariable int id, @RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setStatus(doctorRequest.getStatus());
        Doctor newDoctor = doctorService.setStatusDoctor(id, doctor);
        return ResponseEntity.ok(convertToDTO(newDoctor));
    }

    @DeleteMapping("/delete_doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Đã xóa thành công bác sĩ với ID: " + id);
    }

    private DoctorDTO convertToDTO(Doctor doctor) {
        return new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getRoom(),
                doctor.getExperience(),
                doctor.getStatus()
        );
    }

}
