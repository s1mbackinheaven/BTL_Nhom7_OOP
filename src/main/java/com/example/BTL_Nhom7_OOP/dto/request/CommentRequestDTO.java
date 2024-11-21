package com.example.BTL_Nhom7_OOP.dto.request;

public class CommentRequestDTO {
    private String content;
    private String author;
    private Long articleId; // Thêm trường articleId

    // Constructor mặc định
    public CommentRequestDTO() {
    }

    // Constructor có tham số
    public CommentRequestDTO(String content, String author, Long articleId) {
        this.content = content;
        this.author = author;
        this.articleId = articleId;
    }

    // Getter và Setter
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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}