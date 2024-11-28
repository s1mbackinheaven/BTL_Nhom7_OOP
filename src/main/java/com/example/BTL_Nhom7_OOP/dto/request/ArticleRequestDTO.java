package com.example.BTL_Nhom7_OOP.dto.request;

public class ArticleRequestDTO {
    private String title;
    private String content;
    private String author;

    // Constructor mặc định
    public ArticleRequestDTO() {
    }

    // Constructor có tham số
    public ArticleRequestDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Thêm các getter và setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}