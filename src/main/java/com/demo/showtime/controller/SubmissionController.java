package com.demo.showtime.controller;

import com.demo.showtime.api.Client;
import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.dao.ProgressRepository;
import com.demo.showtime.dao.TaskRepository;
import com.demo.showtime.dao.TestCaseRepository;
import com.demo.showtime.model.*;
import com.demo.showtime.service.CheckerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by atujicov on 9/20/2017.
 */
@Controller
public class SubmissionController {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);
    @Autowired
    private Client client;
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CheckerService checkerService;
    @Autowired
    private ProgressRepository progressRepository;
    @Value("${submission.lang}")
    private Integer lang;

    @RequestMapping(value = "/checker/submission", method = POST)
    @ResponseBody
    public ResponseEntity<?> submit(@RequestBody @Valid SubmissionRequest submissionRequest, BindingResult bindingResult) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            logger.error("submission request is invalid {}", submissionRequest);
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        Task task = taskRepository.findOne(submissionRequest.getTaskId());
        if (task == null) {
            logger.error("submission request invalid task id {}", submissionRequest.getTaskId());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<TestCase> tc = testCaseRepository.findTestCaseByTask(task);
        List<String> inputs = tc.stream().map(TestCase::getInput).collect(Collectors.toList());
        List<String> outputs = tc.stream().map(TestCase::getOutput).collect(Collectors.toList());

        Response response = client.submit(submissionRequest.getSource(), objectMapper.writeValueAsString(inputs));
        SubmissionResult submissionResult = checkerService.processResponse(response, inputs, outputs);
        ProgressId progressId = new ProgressId();
        progressId.setTask(task);
        User user = new User();
        user.setUsername("admin.user"); //TODO
        progressId.setUser(user);
        Progress progress = progressRepository.findOne(progressId);
        if (progress == null) {
            progress = new Progress();
            progress.setPk(progressId);
        }
        if (submissionResult.isValid()) {
            progress.setLanguage(lang);
            progress.setSolution(submissionRequest.getSource());
        }
        progress.setAttempts(progress.getAttempts() + 1);
        progressRepository.save(progress);

        return new ResponseEntity<>(submissionResult, HttpStatus.OK);
    }
}
