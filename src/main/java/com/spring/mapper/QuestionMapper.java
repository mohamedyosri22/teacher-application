package com.spring.mapper;

import com.spring.dto.requests.QuestionRequest;
import com.spring.dto.responses.QuestionResponse;
import com.spring.model.Question;
import com.spring.repository.LevelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionMapper {
    private final LevelRepo levelRepo;
    public Question toQuestion(QuestionRequest request){
        var lvl = levelRepo.findByName(request.lvlName())
                .orElseThrow(()->new EntityNotFoundException("Question Not Found"));

        return Question.builder()
                .title(request.title())
                .answer(request.answer())
                .questionLvl(lvl)
                .addedAt(LocalDateTime.now())
                .build();
    }

    public QuestionResponse toQuestionResponse(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .answer(question.getAnswer())
                .lvlName(question.getQuestionLvl().getName())
                .addedAt(question.getAddedAt())
                .build();
    }
}
