package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.request.ArticleRequestDTO;
import com.example.BTL_Nhom7_OOP.dto.response.ArticleResponseDTO;
import com.example.BTL_Nhom7_OOP.dto.response.CommentResponseDTO;
import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.entity.Comment;
import com.example.BTL_Nhom7_OOP.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleResponseDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponseDTO getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }

    @Transactional
    public ArticleResponseDTO createArticle(ArticleRequestDTO articleRequestDTO) {
        Article article = convertToEntity(articleRequestDTO);
        Article savedArticle = articleRepository.save(article);
        return convertToResponseDTO(savedArticle);
    }

    @Transactional
    public ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        article.setTitle(articleRequestDTO.getTitle());
        article.setContent(articleRequestDTO.getContent());
        article.setAuthor(articleRequestDTO.getAuthor());

        Article updatedArticle = articleRepository.save(article);
        return convertToResponseDTO(updatedArticle);
    }

    @Transactional
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new RuntimeException("Article not found");
        }
        articleRepository.deleteById(id);
    }

    private ArticleResponseDTO convertToResponseDTO(Article article) {
        return new ArticleResponseDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthor(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getComments().stream()
                        .map(this::convertToCommentResponseDTO)
                        .collect(Collectors.toList())
        );
    }

    private Article convertToEntity(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        article.setTitle(articleRequestDTO.getTitle());
        article.setContent(articleRequestDTO.getContent());
        article.setAuthor(articleRequestDTO.getAuthor());
        return article;
    }

    private CommentResponseDTO convertToCommentResponseDTO(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getArticle().getId()
        );
    }
}