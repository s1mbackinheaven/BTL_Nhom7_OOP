package com.example.BTL_Nhom7_OOP.repository;

import com.example.BTL_Nhom7_OOP.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Tìm bài viết theo danh mục
    List<Article> findByCategory(String category);

    // Tìm bài viết theo tiêu đề (không phân biệt hoa thường)
    List<Article> findByTitleContainingIgnoreCase(String keyword);

    // Lấy các bài viết đã xuất bản
    List<Article> findByIsPublishedTrue();

    // Lấy tất cả bài viết sắp xếp theo thời gian tạo giảm dần
    List<Article> findAllByOrderByCreatedAtDesc();
}
