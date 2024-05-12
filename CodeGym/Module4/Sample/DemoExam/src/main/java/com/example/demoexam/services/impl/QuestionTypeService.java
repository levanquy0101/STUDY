package com.example.demoexam.services.impl;

import com.example.demoexam.models.QuestionType;
import com.example.demoexam.repositories.IQuestionTypeRepository;
import com.example.demoexam.services.IQuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeService implements IQuestionTypeService {
    @Autowired
    private IQuestionTypeRepository iQuestionTypeRepository;

    @Override
    public List<QuestionType> findAll() {
        return iQuestionTypeRepository.findAll();
    }

    @Override
    public void save(QuestionType questionType) {
        iQuestionTypeRepository.save(questionType);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public QuestionType findById(Long id) {
        return null;
    }
}
