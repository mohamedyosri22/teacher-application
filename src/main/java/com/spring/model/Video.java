package com.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "video_lvl")
    private Level videoLvl;
    private LocalDateTime addedAt;
    @ManyToMany(mappedBy = "watchedVideos")
    private Set<User> users;
}
