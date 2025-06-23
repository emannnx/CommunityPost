package com.example.ProjectSearchApiHeallthHub.Repos;

import com.example.ProjectSearchApiHeallthHub.Model.CommunityPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommunityPostRepo extends MongoRepository<CommunityPost, String> {
    List<CommunityPost> findByUsernameIgnoreCase(String username);
    List<CommunityPost> findByLocationIgnoreCase(String location);
    List<CommunityPost> findByContentContainingIgnoreCase(String keyword);
    List<CommunityPost> findByCategoryIgnoreCase(String category);
}

