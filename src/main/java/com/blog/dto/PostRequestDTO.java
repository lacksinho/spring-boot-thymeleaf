package com.blog.dto;

import com.blog.model.Author;
import com.blog.model.Category;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PostRequestDTO {

    private Long id;

    private String title;


    private String content;

    private LocalDateTime createdAt;

    private Author author;

    private Category category;
}
