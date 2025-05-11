package com.spring.service;

import com.spring.dto.paging.PagingResponse;
import com.spring.dto.paging.QuestionPagingResponse;
import com.spring.dto.requests.QuestionRequest;
import com.spring.dto.requests.VideoRequest;
import com.spring.dto.responses.QuestionResponse;
import com.spring.dto.responses.VideoResponse;
import com.spring.mapper.QuestionMapper;
import com.spring.mapper.VideoMapper;
import com.spring.model.Question;
import com.spring.model.Video;
import com.spring.repository.LevelRepo;
import com.spring.repository.QuestionRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepo questionRepo;
    private final LevelRepo levelRepo;
    private final QuestionMapper mapper;

    public QuestionResponse addQuestion(QuestionRequest request){
        Question question = mapper.toQuestion(request);

        return mapper.toQuestionResponse(questionRepo.save(question));
    }

    public void deleteQuestion(long id){
        Question question = questionRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Question not found"));
        questionRepo.delete(question);
    }

    public QuestionResponse getById(long id){
        return mapper.toQuestionResponse(questionRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Question not found")));
    }

    public List<QuestionResponse> getQuestionsByLvl(String lvl){
        var level = levelRepo.findByName(lvl)
                .orElseThrow(()->new EntityNotFoundException("Level not found"));
        return questionRepo.findByQuestionLvl_Name(lvl)
                .stream()
                .map(mapper::toQuestionResponse)
                .toList();
    }

    public QuestionPagingResponse getAllQuestions(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize,
                Sort.by("addedAt").descending());

        Page<Question> questions = questionRepo.findAll(pageable);

        List<QuestionResponse> list = questions
                .getContent()
                .stream()
                .map(mapper::toQuestionResponse)
                .toList();

        return QuestionPagingResponse.builder()
                .questions(list)
                .pageNo(questions.getNumber())
                .isLast(questions.isLast())
                .pageSize(questions.getSize())
                .totalElements(questions.getTotalElements())
                .totalPages(questions.getTotalPages())
                .build();
    }


    public QuestionResponse updateQuestion(long id, QuestionRequest request) {
        var question = questionRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Question not found"));

        var lvl = levelRepo.findByName(request.lvlName())
                .orElseThrow(()-> new EntityNotFoundException("Level not found"));

        question.setQuestionLvl(lvl);
        question.setTitle(request.title());
        question.setAnswer(request.answer());

        return mapper.toQuestionResponse(questionRepo.save(question));
    }
}
