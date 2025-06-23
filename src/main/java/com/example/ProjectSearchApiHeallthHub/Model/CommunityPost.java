package com.example.ProjectSearchApiHeallthHub.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "CommunityPosts")
public class CommunityPost {

    @Id
    private String id;
    private String content;
    private String imageUrl;
    private String username;
    private String location;
    private LocalDateTime timestamp;
    private int likes;
    private String category; // ✅ Added category field

    public CommunityPost() {}

    // ✅ Updated constructor with category
    public CommunityPost(String content, String imageUrl, String username, String location, LocalDateTime timestamp, int likes, String category) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.username = username;
        this.location = location;
        this.timestamp = timestamp;
        this.likes = likes;
        this.category = category;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public String getCategory() { return category; } // ✅ Getter
    public void setCategory(String category) { this.category = category; } // ✅ Setter
}
