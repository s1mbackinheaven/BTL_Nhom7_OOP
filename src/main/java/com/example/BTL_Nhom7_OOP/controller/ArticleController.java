package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.request.ArticleRequestDTO;
import com.example.BTL_Nhom7_OOP.dto.response.ArticleResponseDTO;
import com.example.BTL_Nhom7_OOP.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Lấy tất cả bài viết
    @GetMapping("")
    public ResponseEntity<List<ArticleResponseDTO>> getAllArticles() {
        List<ArticleResponseDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    // Tạo bài viết
    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleRequestDTO articleRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        ArticleResponseDTO createdArticle = articleService.createArticle(articleRequestDTO);
        return ResponseEntity.ok(createdArticle);
    }

    // Lấy bài viết theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id) {
        ArticleResponseDTO articleResponseDTO = articleService.getArticleById(id);
        if (articleResponseDTO != null) {
            return ResponseEntity.ok(articleResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bài viết không tồn tại với ID: " + id);
        }
    }


    // Cập nhật bài viết
    @PutMapping("/update_article/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleRequestDTO articleRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        ArticleResponseDTO updatedArticle = articleService.updateArticle(id, articleRequestDTO);
        if (updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle); // trả về đối tượng bài viết đã cập nhật
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bài viết để cập nhật.");
    }

    // Xóa bài viết
    @DeleteMapping("/delete_article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("Bài viết đã được xóa thành công!");
    }
}
