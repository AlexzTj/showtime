package com.demo.showtime.controller;

import com.demo.showtime.dao.CategoryRepository;
import com.demo.showtime.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by atujicov on 9/27/2017.
 */
@RestController
@RequestMapping(value = "/rest/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @RequestMapping
    public ResponseEntity<?> getAllCategories() {
        Iterable<Category> all = categoryRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
