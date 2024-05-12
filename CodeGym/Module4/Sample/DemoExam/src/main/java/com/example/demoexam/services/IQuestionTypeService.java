package com.example.demoexam.services;

import com.example.demoexam.models.QuestionType;

import java.util.List;

public interface IQuestionTypeService {
    List<QuestionType> findAll();
    void save(QuestionType questionType);
    void deleteById(Long id);
    QuestionType findById(Long id);
}
