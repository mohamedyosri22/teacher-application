package com.spring.controller;

import com.spring.dto.requests.LevelRequest;
import com.spring.dto.responses.LevelResponse;
import com.spring.model.Level;
import com.spring.service.LevelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @PostMapping
    public ResponseEntity<LevelResponse> addLevel(@RequestBody LevelRequest lvlRequest){
        return ResponseEntity.ok(levelService.save(lvlRequest));
    }

    @PutMapping
    public ResponseEntity<LevelResponse> updateLevel(@RequestBody LevelRequest lvlRequest){
        return ResponseEntity.ok(levelService.updateLevel(lvlRequest));
    }

    @GetMapping
    public ResponseEntity<List<LevelResponse>> getAllLevels(){
        return ResponseEntity.ok(levelService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<LevelResponse> getByName(@PathVariable String name){
        return ResponseEntity.ok(levelService.findByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable int id){

        levelService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
