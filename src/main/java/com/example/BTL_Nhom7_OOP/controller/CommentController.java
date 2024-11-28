package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.request.CommentRequestDTO;
import com.example.BTL_Nhom7_OOP.dto.response.CommentResponseDTO;
import com.example.BTL_Nhom7_OOP.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Lấy tất cả comment
    @GetMapping("")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {
        List<CommentResponseDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    // Tạo comment
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequestDTO commentRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        CommentResponseDTO createdComment = commentService.createComment(commentRequestDTO);
        return ResponseEntity.ok(createdComment);
    }

    // Lấy comment theo ID
    @GetMapping("/get_comment/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long id) {
        Optional<CommentResponseDTO> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Cập nhật comment
    @PutMapping("/update_comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO commentRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        CommentResponseDTO updatedComment = commentService.updateComment(id, commentRequestDTO);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment); // trả về đối tượng comment đã cập nhật
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy comment để cập nhật.");
    }

    // Xóa comment
    @DeleteMapping("/delete_comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment đã được xóa thành công!");
    }
}