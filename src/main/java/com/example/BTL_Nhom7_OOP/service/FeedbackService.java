package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.request.FeedbackRequestDTO;
import com.example.BTL_Nhom7_OOP.dto.response.FeedbackResponseDTO;
import com.example.BTL_Nhom7_OOP.entity.Feedback;
import com.example.BTL_Nhom7_OOP.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<FeedbackResponseDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<FeedbackResponseDTO> getFeedbackById(Long id) {
        return feedbackRepository.findById(id).map(this::convertToResponseDTO);
    }

    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO feedbackRequestDTO) {
        Feedback feedback = convertToEntity(feedbackRequestDTO);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return convertToResponseDTO(savedFeedback);
    }

    public FeedbackResponseDTO updateFeedback(Long id, FeedbackRequestDTO feedbackRequestDTO) {
        Optional<Feedback> existingFeedbackOpt = feedbackRepository.findById(id);
        if (existingFeedbackOpt.isPresent()) {
            Feedback existingFeedback = existingFeedbackOpt.get();
            existingFeedback.setUsername(feedbackRequestDTO.getUsername());
            existingFeedback.setComment(feedbackRequestDTO.getComment());
            existingFeedback.setRating(feedbackRequestDTO.getRating());
            Feedback savedFeedback = feedbackRepository.save(existingFeedback);
            return convertToResponseDTO(savedFeedback);
        }
        return null;
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    private FeedbackResponseDTO convertToResponseDTO(Feedback feedback) {
        return new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getUsername(),
                feedback.getComment(),
                feedback.getRating(),
                feedback.isApproved(),
                feedback.getCreatedAt(),
                feedback.getUpdatedAt()
        );
    }

    private Feedback convertToEntity(FeedbackRequestDTO feedbackRequestDTO) {
        return new Feedback(
                feedbackRequestDTO.getUsername(),
                feedbackRequestDTO.getComment(),
                feedbackRequestDTO.getRating()
        );
    }
}