package com.example.demoexam.controllers;

import com.example.demoexam.models.Product;
import com.example.demoexam.services.ICategoryService;
import com.example.demoexam.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/{id}")
    public ResponseEntity<Product> productDetail(@PathVariable Long id) {
        Product product = iProductService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> showList() {
        List<Product> list = iProductService.findAll();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Product product) {
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iProductService.save(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = iProductService.findById(id);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
