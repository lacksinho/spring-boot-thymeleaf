package com.blog.service;

import com.blog.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment save(Comment comment);
    void deleteById(Long id);
}
