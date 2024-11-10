package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    // CREATE: Tạo bài viết mới
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    // READ: Lấy tất cả bài viết
    public List<Article> getAllArticles() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    // READ: Lấy bài viết theo ID
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    // UPDATE: Cập nhật bài viết
    public Article updateArticle(Long id, Article articleDetails) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            Article existingArticle = article.get();
            existingArticle.setTitle(articleDetails.getTitle());
            existingArticle.setContent(articleDetails.getContent());
            existingArticle.setCategory(articleDetails.getCategory());
            existingArticle.setThumbnailUrl(articleDetails.getThumbnailUrl());
            existingArticle.setPublished(articleDetails.isPublished());
            return articleRepository.save(existingArticle);
        }
        return null;
    }

    // DELETE: Xóa bài viết
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    // Tìm bài viết theo category
    public List<Article> getArticlesByCategory(String category) {
        return articleRepository.findByCategory(category);
    }

    // Tìm kiếm bài viết theo từ khóa trong tiêu đề
    public List<Article> searchArticles(String keyword) {
        return articleRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // Lấy các bài viết đã xuất bản
    public List<Article> getPublishedArticles() {
        return articleRepository.findByIsPublishedTrue();
    }

    // Tăng số lượt xem bài viết
    public void incrementViewCount(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            Article existingArticle = article.get();
            existingArticle.incrementViewCount();
            articleRepository.save(existingArticle);
        }
    }
}
