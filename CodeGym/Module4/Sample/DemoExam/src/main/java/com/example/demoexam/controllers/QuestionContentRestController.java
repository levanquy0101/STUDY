package com.example.demoexam.controllers;

import com.example.demoexam.models.QuestionContent;
import com.example.demoexam.services.IQuestionContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/questions")
public class QuestionContentRestController {
    @Autowired
    private IQuestionContentService iQuestionContentService;
    @GetMapping("/{id}")
    public ResponseEntity<QuestionContent> getQuestionById(@PathVariable Long id) {
        QuestionContent questionContent = iQuestionContentService.findById(id);
        if (questionContent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionContent);
    }
}
