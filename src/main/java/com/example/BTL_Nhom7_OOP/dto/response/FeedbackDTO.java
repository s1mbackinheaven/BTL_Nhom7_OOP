package com.example.BTL_Nhom7_OOP.dto.response;

import com.example.BTL_Nhom7_OOP.entity.Feedback;

public class FeedbackDTO {
    private int id;
    private String name;
    private String content;
    public FeedbackDTO(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static FeedbackDTO fromEntity(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        return new FeedbackDTO(feedback.getId(), feedback.getName(), feedback.getContent());
    }
}
