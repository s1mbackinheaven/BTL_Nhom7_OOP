package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.ArticleDTO;
import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE: Tạo bài viết mới
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = modelMapper.map(articleDTO, Article.class);
        Article savedArticle = articleRepository.save(article);
        return modelMapper.map(savedArticle, ArticleDTO.class);
    }

    // READ: Lấy tất cả bài viết
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc();
        return articles.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    // READ: Lấy bài viết theo ID
    public Optional<ArticleDTO> getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.map(value -> modelMapper.map(value, ArticleDTO.class));
    }

    // UPDATE: Cập nhật bài viết
    public ArticleDTO updateArticle(Long id, ArticleDTO articleDTO) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            Article existingArticle = article.get();
            existingArticle.setTitle(articleDTO.getTitle());
            existingArticle.setContent(articleDTO.getContent());
            existingArticle.setCategory(articleDTO.getCategory());
            existingArticle.setThumbnailUrl(articleDTO.getThumbnailUrl());
            existingArticle.setPublished(articleDTO.isPublished());
            Article updatedArticle = articleRepository.save(existingArticle);
            return modelMapper.map(updatedArticle, ArticleDTO.class);
        }
        return null;
    }

    // DELETE: Xóa bài viết
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    // Tìm bài viết theo category
    public List<ArticleDTO> getArticlesByCategory(String category) {
        List<Article> articles = articleRepository.findByCategory(category);
        return articles.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    // Tìm kiếm bài viết theo từ khóa trong tiêu đề
    public List<ArticleDTO> searchArticles(String keyword) {
        List<Article> articles = articleRepository.findByTitleContainingIgnoreCase(keyword);
        return articles.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    // Lấy các bài viết đã xuất bản
    public List<ArticleDTO> getPublishedArticles() {
        List<Article> articles = articleRepository.findByIsPublishedTrue();
        return articles.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
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
