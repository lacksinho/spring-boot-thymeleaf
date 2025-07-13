package com.blog.controller;

import com.blog.model.Category;
import com.blog.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/create";
    }

//    @PostMapping
//    public String createCategory(@ModelAttribute Category category) {
//        categoryService.save(category);
//        return "redirect:/categories";
//    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok("Saved");
    }


    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
