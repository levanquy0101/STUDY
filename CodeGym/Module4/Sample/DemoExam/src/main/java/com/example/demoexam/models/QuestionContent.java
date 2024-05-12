package com.example.demoexam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class QuestionContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    @NotBlank(message = "Trường không được để trống!")
    @Size(min = 5, max = 100, message = "Tiêu đề phải từ 5 - 100 ký tự")
    private String title;
    @Column
    @NotBlank(message = "Trường không được để trống!")
    @Size(min = 10, max = 500, message = "Nội dung phải từ 10 - 500 ký tự")
    private String content;
    @Column
    private String answer;
    @ManyToOne
    @NotNull(message = "Trường không được để trống!")
    @JoinColumn
    private QuestionType questionType;
    @Column
    private LocalDate dateCreate;
    @Column
    private String status;

    public QuestionContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
