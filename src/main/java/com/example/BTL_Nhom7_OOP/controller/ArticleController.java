package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.entity.Article;
import com.example.BTL_Nhom7_OOP.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Hiển thị danh sách bài viết
    @GetMapping("")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "admin/articles/list";
    }

    // Hiển thị form tạo bài viết mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new Article());
        return "admin/articles/create";
    }

    // Xử lý tạo bài viết mới
    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute("article") Article article,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/articles/create";
        }
        articleService.createArticle(article);
        redirectAttributes.addFlashAttribute("success", "Bài viết đã được tạo thành công!");
        return "redirect:/admin/articles";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Article> article = articleService.getArticleById(id);
        if (article.isPresent()) {
            model.addAttribute("article", article.get());
            return "admin/articles/edit";
        }
        return "redirect:/admin/articles";
    }

    // Xử lý cập nhật bài viết
    @PostMapping("/edit/{id}")
    public String updateArticle(@PathVariable Long id,
                                @Valid @ModelAttribute("article") Article article,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/articles/edit";
        }
        articleService.updateArticle(id, article);
        redirectAttributes.addFlashAttribute("success", "Bài viết đã được cập nhật thành công!");
        return "redirect:/admin/articles";
    }

    // Xóa bài viết
    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        redirectAttributes.addFlashAttribute("success", "Bài viết đã được xóa thành công!");
        return "redirect:/admin/articles";
    }

    // Tìm kiếm bài viết
    @GetMapping("/search")
    public String searchArticles(@RequestParam String keyword, Model model) {
        model.addAttribute("articles", articleService.searchArticles(keyword));
        model.addAttribute("keyword", keyword);
        return "admin/articles/list";
    }

    // Hiển thị bài viết theo category
    @GetMapping("/category/{category}")
    public String getArticlesByCategory(@PathVariable String category, Model model) {
        model.addAttribute("articles", articleService.getArticlesByCategory(category));
        model.addAttribute("currentCategory", category);
        return "admin/articles/list";
    }
}
