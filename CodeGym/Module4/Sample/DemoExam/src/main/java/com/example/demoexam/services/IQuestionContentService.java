package com.example.demoexam.services;

import com.example.demoexam.models.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQuestionContentService {
    List<QuestionContent> findAll();
    List<QuestionContent> findByNameContaining(String name);
    Page<QuestionContent> findAllSearch(String nameKeyword, String codeKeyword, Pageable pageable);
    List<QuestionContent> findByNameContainingOrCodeNameContaining(String nameKeyword, String codeKeyword);
    void save(QuestionContent questionContent);
    void deleteById(Long id);

    QuestionContent findById(Long id);
}
