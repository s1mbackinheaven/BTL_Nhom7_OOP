package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.entity.Comment;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.ArticleRepository;
import com.example.BTL_Nhom7_OOP.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Comment createComment(Comment comment, int id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy"));
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Comment> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy");
        }
        return comments;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Comment getCommentById(int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCommentById(int id) {
        commentRepository.deleteById(id);
    }
}
