package com.example.BTL_Nhom7_OOP.repository;

import com.example.BTL_Nhom7_OOP.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
