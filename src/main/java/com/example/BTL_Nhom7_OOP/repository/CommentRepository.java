package com.example.BTL_Nhom7_OOP.repository;


import com.example.BTL_Nhom7_OOP.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
