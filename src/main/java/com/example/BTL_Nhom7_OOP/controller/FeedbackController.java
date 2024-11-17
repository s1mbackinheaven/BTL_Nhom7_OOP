package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.FeedbackDTO;
import com.example.BTL_Nhom7_OOP.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedbackDTO));
    }

    //lấy hết fb
    @GetMapping("")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    //lấy fb theo id
    @GetMapping("/get_feedback/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @GetMapping("/approved")
    public ResponseEntity<List<FeedbackDTO>> getApprovedFeedbacks() {
        return ResponseEntity.ok(feedbackService.getApprovedFeedbacks());
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Void> approveFeedback(@PathVariable Long id) {
        feedbackService.approveFeedback(id);
        return ResponseEntity.ok().build();
    }

    //xoa danh gia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }
}
