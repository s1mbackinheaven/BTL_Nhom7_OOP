package com.example.BTL_Nhom7_OOP.dto.response;

import java.time.LocalDateTime;

public class CommentResponseDTO {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long articleId; // Thêm trường articleId

    // Constructor mặc định
    public CommentResponseDTO() {
    }

    // Constructor có tham số
    public CommentResponseDTO(Long id, String content, String author, LocalDateTime createdAt, Long articleId) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.articleId = articleId;
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}