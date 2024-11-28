package com.example.BTL_Nhom7_OOP.dto.request;

import java.time.LocalDateTime;

public class FeedbackRequestDTO {
    private String username;
    private String comment;
    private int rating;

    // Constructor mặc định
    public FeedbackRequestDTO() {
    }

    // Constructor có tham số
    public FeedbackRequestDTO(String username, String comment, int rating) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
    }

    // Getter và Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}