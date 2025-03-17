package com.blogbackend.auth.web.controller;

import com.blogbackend.auth.persistence.entity.UserEntity;
import com.blogbackend.auth.service.BlogService;
import com.blogbackend.auth.service.UserService;
import com.blogbackend.auth.service.dto.BlogDto;
import com.blogbackend.auth.service.dto.BlogRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllPublicBlogs(){
        return ResponseEntity.ok(this.blogService.getPublics());
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogRequestDto blogRequest, @RequestParam Long userId){
        UserEntity userEntity = userService.getUserById(userId);
        BlogDto blogDto = blogService.createBlog(blogRequest, userEntity);
        return new ResponseEntity<>(blogDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BlogDto> updateBlog(@RequestBody BlogRequestDto blogRequest, @RequestParam Long blogId){
        BlogDto updatedBlog = blogService.updateBlog(blogId, blogRequest);
        return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long blogId){
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
