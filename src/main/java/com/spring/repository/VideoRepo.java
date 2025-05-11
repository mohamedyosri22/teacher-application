package com.spring.repository;

import com.spring.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video,Long> {
    public List<Video> findByVideoLvlNameOrderByAddedAt(String lvl);
}
