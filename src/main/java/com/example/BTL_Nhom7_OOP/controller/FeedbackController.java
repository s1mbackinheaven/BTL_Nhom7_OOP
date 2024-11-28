package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.request.FeedbackRequestDTO;
import com.example.BTL_Nhom7_OOP.dto.response.FeedbackResponseDTO;
import com.example.BTL_Nhom7_OOP.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Lấy tất cả feedback
    @GetMapping("")
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedbacks() {
        List<FeedbackResponseDTO> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    // Tạo feedback
    @PostMapping("/create")
    public ResponseEntity<?> createFeedback(@Valid @RequestBody FeedbackRequestDTO feedbackRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        FeedbackResponseDTO createdFeedback = feedbackService.createFeedback(feedbackRequestDTO);
        return ResponseEntity.ok(createdFeedback);
    }

    // Lấy feedback theo ID
    @GetMapping("/get_feedback/{id}")
    public ResponseEntity<FeedbackResponseDTO> getFeedbackById(@PathVariable Long id) {
        Optional<FeedbackResponseDTO> feedback = feedbackService.getFeedbackById(id);
        return feedback.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Cập nhật feedback
    @PutMapping("/update_feedback/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackRequestDTO feedbackRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        FeedbackResponseDTO updatedFeedback = feedbackService.updateFeedback(id, feedbackRequestDTO);
        if (updatedFeedback != null) {
            return ResponseEntity.ok(updatedFeedback); // trả về đối tượng feedback đã cập nhật
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy feedback để cập nhật.");
    }

    // Xóa feedback
    @DeleteMapping("/delete_feedback/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("Feedback đã được xóa thành công!");
    }
}