package com.example.BTL_Nhom7_OOP.repository;

import com.example.BTL_Nhom7_OOP.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByApproved(boolean approved);
}
