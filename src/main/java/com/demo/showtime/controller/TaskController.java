package com.demo.showtime.controller;

import com.demo.showtime.dao.TaskRepository;
import com.demo.showtime.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by atujicov on 9/14/2017.
 */
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private static final Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "createdOn"));
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE = 0;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping
    public ResponseEntity<?> getAllTasksNoPage() {
        return getAllTasks(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
    }

    @RequestMapping(params = {"page", "size"})
    public ResponseEntity<?> getAllTasks(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("Fetching all tasks");
        if (page < 0) page = DEFAULT_PAGE;
        if (size <= 0) size = DEFAULT_PAGE_SIZE;
        Pageable pageable = new PageRequest(page, size, sort);
        Page<Task> all = taskRepository.findAll(pageable);

        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
        logger.info("Fetching task with id {}", id);
        Task one = taskRepository.findOne(id);
        if (one == null) {
            logger.info("Failed fetching task with id {}", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task, BindingResult result) {
        logger.info("Creating task {}", task);
        return saveOrUpdate(task, result);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<?> updateTask(@Valid @RequestBody Task task, BindingResult result) {
        logger.info("Updating task {}", task);
        return saveOrUpdate(task, result);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        logger.info("Fetching & Deleting task with id {}", id);
        taskRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> saveOrUpdate(Task task, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Failed saving/updating task {}", task);
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
