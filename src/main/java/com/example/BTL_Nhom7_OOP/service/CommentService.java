package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.request.CommentRequestDTO;
import com.example.BTL_Nhom7_OOP.dto.response.CommentResponseDTO;
import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.entity.Comment;
import com.example.BTL_Nhom7_OOP.repository.ArticleRepository;
import com.example.BTL_Nhom7_OOP.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentResponseDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommentResponseDTO> getCommentById(Long id) {
        return commentRepository.findById(id).map(this::convertToResponseDTO);
    }

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Comment comment = convertToEntity(commentRequestDTO);
        Comment savedComment = commentRepository.save(comment);
        return convertToResponseDTO(savedComment);
    }

    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO) {
        Optional<Comment> existingCommentOpt = commentRepository.findById(id);
        if (existingCommentOpt.isPresent()) {
            Comment existingComment = existingCommentOpt.get();
            existingComment.setContent(commentRequestDTO.getContent());
            existingComment.setAuthor(commentRequestDTO.getAuthor());
            Comment savedComment = commentRepository.save(existingComment);
            return convertToResponseDTO(savedComment);
        }
        return null;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentResponseDTO convertToResponseDTO(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getArticle().getId()
        );
    }

    private Comment convertToEntity(CommentRequestDTO commentRequestDTO) {
        Optional<Article> articleOpt = articleRepository.findById(commentRequestDTO.getArticleId());
        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            return new Comment(commentRequestDTO.getContent(), commentRequestDTO.getAuthor(), article);
        }
        throw new IllegalArgumentException("Article not found");
    }
}