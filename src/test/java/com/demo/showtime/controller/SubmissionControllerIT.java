package com.demo.showtime.controller;

import com.demo.showtime.ShowtimeApplication;
import com.demo.showtime.api.Client;
import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.model.SubmissionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by atujicov on 9/21/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:testdata.sql")
public class SubmissionControllerIT {
    @MockBean
    private Client client;
    private ArgumentCaptor<String> testCaseCaptor = ArgumentCaptor.forClass(String.class);

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void submit() throws JsonProcessingException {
        Response response = new Response();
        when(client.submit(any(),any())).thenReturn(response);
        SubmissionRequest request = new SubmissionRequest();
        request.setSource("print 1");
        request.setTaskId(1);
        ResponseEntity rs = restTemplate.postForEntity("/checker/submission", request, ResponseEntity.class);
        verify(client).submit(anyString(),testCaseCaptor.capture());
        String actualTC = testCaseCaptor.getValue();
        assertEquals("[\"1\",\"3\"]",actualTC);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(request));;
    }
}
