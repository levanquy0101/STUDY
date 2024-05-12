package com.example.demoexam.services.impl;

import com.example.demoexam.models.QuestionContent;
import com.example.demoexam.repositories.IQuestionContentRepository;
import com.example.demoexam.services.IQuestionContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionContentService implements IQuestionContentService {
    @Autowired
    private IQuestionContentRepository iQuestionContentRepository;
    @Override
    public List<QuestionContent> findAll() {
        return iQuestionContentRepository.findAll();
    }

    @Override
    public List<QuestionContent> findByNameContaining(String name) {
        return null;
    }

    @Override
    public Page<QuestionContent> findAllSearch(String nameKeyword, String codeKeyword, Pageable pageable) {
        return iQuestionContentRepository.findByTitleContainingOrQuestionType_NameContaining(nameKeyword,codeKeyword,pageable);
    }

    @Override
    public List<QuestionContent> findByNameContainingOrCodeNameContaining(String nameKeyword, String codeKeyword) {
        return null;
    }

    @Override
    public void save(QuestionContent questionContent) {
        iQuestionContentRepository.save(questionContent);
    }

    @Override
    public void deleteById(Long id) {
        iQuestionContentRepository.deleteById(id);
    }

    @Override
    public QuestionContent findById(Long id) {
        return iQuestionContentRepository.findById(id).orElse(null);
    }


}
