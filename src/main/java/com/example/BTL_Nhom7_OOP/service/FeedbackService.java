package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.entity.Feedback;
import com.example.BTL_Nhom7_OOP.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
