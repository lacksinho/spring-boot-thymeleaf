package com.blog.controller;

import com.blog.model.Author;
import com.blog.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/create";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
