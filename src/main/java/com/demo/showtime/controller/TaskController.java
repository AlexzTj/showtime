package com.demo.showtime.controller;

import com.demo.showtime.dao.CategoryRepository;
import com.demo.showtime.dao.TaskRepository;
import com.demo.showtime.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by atujicov on 9/27/2017.
 */
@Controller
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/tasks/{id}")
    public ModelAndView getById(@PathVariable Integer id) {
        logger.info("Fetching task with id {}", id);
        Task one = taskRepository.findOne(id);
        if (one == null) {
            logger.info("Failed fetching task with id {}", id);
        }
        ModelAndView modelAndView = new ModelAndView("task");
        modelAndView.addObject("task", one);
        return modelAndView;
    }
}
