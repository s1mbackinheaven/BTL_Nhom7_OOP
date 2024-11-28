package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Article> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            throw new ResourceNotFoundException("Không có bài viết nào");
        }
        return articles;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Article getArticleById(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Article> getArticleByTitle(String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        if (articles.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy");
        }
        return articles;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Article> getArticleByAuthor(String author) {
        List<Article> articles = articleRepository.findByAuthor(author);
        if (articles.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy");
        }
        return articles;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }
}
