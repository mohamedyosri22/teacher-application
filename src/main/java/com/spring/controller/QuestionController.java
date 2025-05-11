package com.spring.controller;

import com.spring.dto.paging.QuestionPagingResponse;
import com.spring.dto.requests.QuestionRequest;
import com.spring.dto.responses.QuestionResponse;
import com.spring.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/by-lvlName/{lvlName}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByLvl(@PathVariable String lvlName){
        return new ResponseEntity<>(questionService.getQuestionsByLvl(lvlName)
                ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> addQuestion(@RequestBody QuestionRequest request) {
        return new ResponseEntity<>(questionService.addQuestion(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<QuestionPagingResponse> getAllVideos(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo
            , @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(questionService.getAllQuestions(pageNo, pageSize));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<QuestionResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam("id")long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateVideoData(@PathVariable long id,@RequestBody QuestionRequest request){
        return ResponseEntity.ok(questionService.updateQuestion(id,request));
    }
}
