package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.request.FeedbackRequest;
import com.example.BTL_Nhom7_OOP.dto.response.FeedbackDTO;
import com.example.BTL_Nhom7_OOP.entity.Feedback;
import com.example.BTL_Nhom7_OOP.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        Feedback feedback = new Feedback();
        feedback.setName(feedbackRequest.getName());
        feedback.setContent(feedbackRequest.getContent());
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.ok(FeedbackDTO.fromEntity(createdFeedback));
    }

    @GetMapping("/get_all_feedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback() {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDTOList.add(FeedbackDTO.fromEntity(feedback));
        }
        return ResponseEntity.ok(feedbackDTOList);
    }
}
