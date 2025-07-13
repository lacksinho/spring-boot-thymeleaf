package com.blog.controller;

import com.blog.model.Comment;
import com.blog.service.CommentService;
import com.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping
    public String listComments(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "comments/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("posts", postService.findAll());
        return "comments/create";
    }

    @PostMapping
    public String createComment(@ModelAttribute Comment comment) {
        commentService.save(comment);
        return "redirect:/comments";
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "redirect:/comments";
    }
}