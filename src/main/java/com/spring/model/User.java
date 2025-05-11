package com.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String picture;
    private String role;
    @ManyToOne
    @JoinColumn(name = "user_lvl")
    private Level userLevel;
    private boolean tookLevelTest;
    private LocalDateTime joinedAt;
    @ManyToMany
    @JoinTable(
            name = "users_videos",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "video_id",referencedColumnName = "id")
    )
    private Set<Video> watchedVideos;
}
