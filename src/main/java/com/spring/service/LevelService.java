package com.spring.service;

import com.spring.dto.requests.LevelRequest;
import com.spring.dto.responses.LevelResponse;
import com.spring.mapper.LevelMapper;
import com.spring.model.Level;
import com.spring.repository.LevelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepo levelRepo;
    private final LevelMapper mapper;

    public LevelResponse save(LevelRequest request){
        return mapper.toLevelResponse(levelRepo
                .save(mapper.toLevel(request)));
    }

    public List<LevelResponse> findAll(){
        return levelRepo.findAll()
                .stream()
                .map(mapper::toLevelResponse)
                .toList();
    }
    public void deleteById(int id){
        levelRepo.deleteById(id);
    }

    public LevelResponse findByName(String name){
        return mapper.toLevelResponse(levelRepo.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Level not found")));
    }

    public LevelResponse updateLevel(LevelRequest request){
        var lvl = levelRepo.findByName(request.lvlName())
                .orElseThrow(()->new EntityNotFoundException("Level not found"));

        lvl.setName(request.lvlName());

        return mapper.toLevelResponse(levelRepo.save(lvl));
    }
}
