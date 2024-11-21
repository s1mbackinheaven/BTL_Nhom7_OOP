package com.example.BTL_Nhom7_OOP.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleRequestDTO {
    private String title;
    private String content;
    private String author;
    private String category;
    private String thumbnailUrl;
    private boolean isPublished;

    // Constructor mặc định
    public ArticleRequestDTO() {
    }

    // Constructor có tham số
    public ArticleRequestDTO(String title, String content, String author, String category, String thumbnailUrl, boolean isPublished) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.thumbnailUrl = thumbnailUrl;
        this.isPublished = isPublished;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
}