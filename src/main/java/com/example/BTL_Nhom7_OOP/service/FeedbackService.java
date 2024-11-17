package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.FeedbackDTO;
import com.example.BTL_Nhom7_OOP.entity.Feedback;
import com.example.BTL_Nhom7_OOP.repository.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE: Tạo fb mới
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return modelMapper.map(savedFeedback, FeedbackDTO.class);
    }

    // READ: Lấy tất cả fb
    public List<FeedbackDTO> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackDTO.class))
                .collect(Collectors.toList());
    }

    // READ: Lấy fb theo ID
    public FeedbackDTO getFeedbackById(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.map(value -> modelMapper.map(value, FeedbackDTO.class)).orElse(null);
    }

    // READ: Lấy các fb đã được duyệt
    public List<FeedbackDTO> getApprovedFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findByApproved(true);
        return feedbacks.stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackDTO.class))
                .collect(Collectors.toList());
    }

    // UPDATE: Cập nhật fb
    public FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            Feedback existingFeedback = feedback.get();
            existingFeedback.setUsername(feedbackDTO.getUsername());
            existingFeedback.setComment(feedbackDTO.getComment());
            existingFeedback.setRating(feedbackDTO.getRating());
            existingFeedback.setApproved(feedbackDTO.isApproved());
            Feedback updatedFeedback = feedbackRepository.save(existingFeedback);
            return modelMapper.map(updatedFeedback, FeedbackDTO.class);
        }
        return null;
    }


    // APPROVE: Duyệt fb
    public void approveFeedback(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            Feedback existingFeedback = feedback.get();
            existingFeedback.setApproved(true);
            feedbackRepository.save(existingFeedback);
        }
    }

    // DELETE: Xóa fb
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
