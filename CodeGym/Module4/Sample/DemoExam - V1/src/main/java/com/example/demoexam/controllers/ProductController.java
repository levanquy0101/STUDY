package com.example.demoexam.controllers;

import com.example.demoexam.models.Product;
import com.example.demoexam.services.ICategoryService;
import com.example.demoexam.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

//    @GetMapping("")
//    public String showListProduct(Model model){
//        List<Product> productList = productService.findAll();
//        model.addAttribute("productList", productList);
//        return "product/list";
//    }

    @GetMapping("")
    public String searchProducts(@RequestParam(name = "nameSearch", defaultValue = "") String nameSearch,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 Model model) {
//        Sort sort = Sort.by("price").ascending();
        if (page < 0) {
            page = 0;
        }
//        Pageable pageable = PageRequest.of(page, size, sort); // Chỉ định sắp xếp trong PageRequest
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productList = productService.findAllSearch(nameSearch, nameSearch, pageable);
        model.addAttribute("productList", productList);
        model.addAttribute("nameSearch", nameSearch);
        return "product/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model){
        Product product = new Product();

        try {
            Product lastProduct = productService.findTopByOrderByCodeNameDesc();
            String lastCode = lastProduct.getCodeName();
            int lastNumber = Integer.parseInt(lastCode.substring(3));
            String newCode = "SP-" + String.format("%03d", lastNumber + 1);
            product.setCodeName(newCode);
        } catch (Exception e) {
            product.setCodeName("SP-001");
        }

        model.addAttribute("product", product);
        model.addAttribute("category", categoryService.findAll());
        return "product/create";
    }

    @GetMapping("{id}/update")
    public String showUpdateForm(@PathVariable Long id,Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("category", categoryService.findAll());
        return "product/update";
    }
    @GetMapping("{id}/delete")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirect) {
        productService.deleteById(id);
        redirect.addFlashAttribute("mess","1");
        return "redirect:/product";
    }
    @GetMapping("/delete")
    public String deleteProducts(@RequestParam("ids") List<Long> ids, RedirectAttributes redirect) {
        productService.deleteByIds(ids);
        redirect.addFlashAttribute("mess","1");
        return "redirect:/product";
    }
    @GetMapping("{id}/detail")
    public String showDetailProduct(@PathVariable Long id, Model model){
        return "product/detail";
    }
//    @GetMapping("/search")
//    public String search(@RequestParam("nameSearch") String nameSearch, Model model){
//        List<Product> productList = productService.findByNameContainingOrCodeNameContaining(nameSearch,nameSearch);
//        model.addAttribute("productList", productList);
//        model.addAttribute("nameSearch",nameSearch);
//        return "product/list";
//    }

    @PostMapping("/create")
    public String createProduct(@Validated @ModelAttribute Product product,BindingResult bindingResult,
                                RedirectAttributes redirect,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryService.findAll());
            return "/product/create";
        }
        productService.save(product);
        redirect.addFlashAttribute("mess","1");
        return "redirect:/product";
    }
    @PostMapping("/update")
    public String updateProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult,
                                RedirectAttributes redirect, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryService.findAll());
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println("Field: " + error.getField());
                System.out.println("Code: " + error.getCode());
                System.out.println("Message: " + error.getDefaultMessage());
            }
            return "/product/update";
        }
        productService.save(product);
        redirect.addFlashAttribute("mess","1");
        return "redirect:/product";
    }
    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "error";
    }

}
