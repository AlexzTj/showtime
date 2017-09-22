package com.demo.showtime.controller;

import com.demo.showtime.api.Client;
import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.dao.TaskRepository;
import com.demo.showtime.dao.TestCaseRepository;
import com.demo.showtime.model.SubmissionRequest;
import com.demo.showtime.model.Task;
import com.demo.showtime.model.TestCase;
import com.demo.showtime.service.CheckerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by atujicov on 9/21/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SubmissionControllerTest {
    @Mock
    private Client client;
    @Mock
    private TestCaseRepository testCaseRepository;
    @Mock
    private TaskRepository taskRepository;
    @Spy
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private CheckerService checkerService;
    @Mock
    private Task task;
    @Mock
    private Response response;
    @Mock
    private BindingResult bindingResult;
    private ArgumentCaptor<String> testCaseCaptor = ArgumentCaptor.forClass(String.class);
    @InjectMocks
    private SubmissionController target;

    @Test
    public void submit() throws JsonProcessingException {
        List<TestCase> testCases = Arrays.asList(new TestCase(task, "1", "2"), new TestCase(task, "2", "4"));
        when(taskRepository.findOne(anyInt())).thenReturn(task);
        when(testCaseRepository.findTestCaseByTask(task)).thenReturn(testCases);
        when(client.submit(any(), any())).thenReturn(response);
        when(bindingResult.hasErrors()).thenReturn(false);

        target.submit(new SubmissionRequest(),bindingResult);

        verify(client).submit(any(),testCaseCaptor.capture());

        assertEquals("[\"1\",\"2\"]",testCaseCaptor.getValue());
    }

}