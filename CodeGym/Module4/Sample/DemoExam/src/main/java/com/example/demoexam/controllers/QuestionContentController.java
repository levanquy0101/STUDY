package com.example.demoexam.controllers;

import com.example.demoexam.models.QuestionContent;
import com.example.demoexam.services.IQuestionContentService;
import com.example.demoexam.services.IQuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/questions")
public class QuestionContentController {
    @Autowired
    private IQuestionContentService iQuestionContentService;
    @Autowired
    private IQuestionTypeService iQuestionTypeService;
    @GetMapping("")
    public String searchProducts(@RequestParam(name = "nameSearch", defaultValue = "") String nameSearch,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 Model model) {
        Sort sort = Sort.by("dateCreate").ascending();
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<QuestionContent> questionContentList = iQuestionContentService.findAllSearch(nameSearch, nameSearch, pageable);
        model.addAttribute("questionContentList", questionContentList);
        model.addAttribute("nameSearch", nameSearch);
        return "product/list";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model){
        QuestionContent questionContent = new QuestionContent();
        model.addAttribute("questionContent",questionContent);
        model.addAttribute("questionType",iQuestionTypeService.findAll());
        return "product/create";
    }
    @PostMapping("/create")
        public String create(@Validated @ModelAttribute QuestionContent questionContent, BindingResult bindingResult,
                             RedirectAttributes redirect, Model model){
        questionContent.setDateCreate(LocalDate.now());
        questionContent.setStatus("Chờ phản hồi");
        if(bindingResult.hasErrors()){
            model.addAttribute("questionType", iQuestionTypeService.findAll());
            return "/product/create";
        }
        iQuestionContentService.save(questionContent);
        redirect.addFlashAttribute("mess","1");
        return "redirect:/questions";
    }
    @GetMapping("{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        iQuestionContentService.deleteById(id);
        redirect.addFlashAttribute("mess","1");
        return "redirect:/questions";
    }
}
