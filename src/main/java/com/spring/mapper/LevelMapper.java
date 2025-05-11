package com.spring.mapper;

import com.spring.dto.requests.LevelRequest;
import com.spring.dto.responses.LevelResponse;
import com.spring.model.Level;
import org.springframework.stereotype.Service;

@Service
public class LevelMapper {
    public LevelResponse toLevelResponse(Level lvl){
        return LevelResponse.builder()
                .lvlName(lvl.getName())
                .id(lvl.getId())
                .build();
    }

    public Level toLevel(LevelRequest request){
        return Level.builder()
                .name(request.lvlName())
                .build();
    }
}
