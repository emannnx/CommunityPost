package com.example.ProjectSearchApiHeallthHub.Controller;

import com.example.ProjectSearchApiHeallthHub.Model.CommunityPost;
import com.example.ProjectSearchApiHeallthHub.Repos.CommunityPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class CommunityPostController {

    @Autowired
    private CommunityPostRepo postRepo;

    // Create new post
    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@RequestBody CommunityPost post) {
        post.setTimestamp(LocalDateTime.now());
        post.setLikes(0);
        CommunityPost newPost = postRepo.save(post);
        return ResponseEntity.ok(newPost);
    }

    // Create multiple posts
    @PostMapping("/createAll")
    public ResponseEntity<List<CommunityPost>> createMultiplePosts(@RequestBody List<CommunityPost> posts) {
        for (CommunityPost post : posts) {
            post.setTimestamp(LocalDateTime.now());
            post.setLikes(0);
        }
        return ResponseEntity.ok(postRepo.saveAll(posts));
    }

    // Get all posts
    @GetMapping("/getAll")
    public List<CommunityPost> getAllPosts() {
        return postRepo.findAll();
    }

    // Search by content keyword
    @GetMapping("/search")
    public ResponseEntity<List<CommunityPost>> searchPosts(@RequestParam("q") String keyword) {
        List<CommunityPost> results = postRepo.findByContentContainingIgnoreCase(keyword);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    // Get posts by username
    @GetMapping("/user/{username}")
    public List<CommunityPost> getByUsername(@PathVariable String username) {
        return postRepo.findByUsernameIgnoreCase(username);
    }

    // Get posts by location
    @GetMapping("/location/{location}")
    public List<CommunityPost> getByLocation(@PathVariable String location) {
        return postRepo.findByLocationIgnoreCase(location);
    }

    // Update a post
    @PutMapping("/update/{id}")
    public ResponseEntity<CommunityPost> updatePost(@PathVariable String id, @RequestBody CommunityPost updatedPost) {
        Optional<CommunityPost> optionalPost = postRepo.findById(id);
        if (optionalPost.isPresent()) {
            CommunityPost post = optionalPost.get();
            post.setContent(updatedPost.getContent());
            post.setImageUrl(updatedPost.getImageUrl());
            post.setLocation(updatedPost.getLocation());
            post.setUsername(updatedPost.getUsername());
            return ResponseEntity.ok(postRepo.save(post));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        if (postRepo.existsById(id)) {
            postRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Like a post
    @PostMapping("/like/{id}")
    public ResponseEntity<CommunityPost> likePost(@PathVariable String id) {
        Optional<CommunityPost> optionalPost = postRepo.findById(id);
        if (optionalPost.isPresent()) {
            CommunityPost post = optionalPost.get();
            post.setLikes(post.getLikes() + 1);
            return ResponseEntity.ok(postRepo.save(post));
        }
        return ResponseEntity.notFound().build();
    }
}
