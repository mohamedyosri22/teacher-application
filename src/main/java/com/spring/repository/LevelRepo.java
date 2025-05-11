package com.spring.repository;

import com.spring.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepo extends JpaRepository<Level,Integer> {
    Optional<Level> findByName(String name);
}
