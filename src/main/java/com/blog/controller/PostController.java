package com.blog.controller;

import com.blog.model.Author;
import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.service.AuthorService;
import com.blog.service.CategoryService;
import com.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public PostController(PostService postService, AuthorService authorService, CategoryService categoryService) {
        this.postService = postService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    // 1. List all posts
    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list"; // -> src/main/resources/templates/posts/list.html
    }

    // 2. Show form to create a post
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "posts/create";
    }

    // 3. Handle form submission
//    @PostMapping
//    public String createPost(@ModelAttribute Post post) {
//        System.out.println(post);
//        postService.save(post);
//        return "redirect:/posts";
//    }

    @PostMapping
    public String createPost(@Validated @ModelAttribute("post") Post post,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            return "posts/create";
        }

        // Fetch Author and Category by ID before saving
        Long authorId = post.getAuthor().getId();
        Long categoryId = post.getCategory().getId();

        post.setAuthor(authorService.findById(authorId));
        post.setCategory(categoryService.findById(categoryId));

        postService.save(post);
        return "redirect:/posts";
    }


    // 4. View post by ID
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "posts/view";
    }

    // 5. Delete post
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
