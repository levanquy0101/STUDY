package com.example.demoexam.repositories;

import com.example.demoexam.models.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IQuestionContentRepository extends JpaRepository<QuestionContent,Long> {
//        Page<QuestionContent> findByNameContainingOrCodeNameContaining(@Param("nameKeyword") String nameKeyword, @Param("codeKeyword") String codeKeyword, Pageable pageable);
        Page<QuestionContent> findByTitleContainingOrQuestionType_NameContaining(@Param("nameKeyword") String nameKeyword, @Param("codeKeyword") String codeKeyword, Pageable pageable);
}
