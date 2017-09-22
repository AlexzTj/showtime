package com.demo.showtime.controller;

import com.demo.showtime.api.Client;
import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.model.*;
import com.demo.showtime.service.CheckerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
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
    @MockBean
    private CheckerService checkerService;

    @Autowired
    private TestRestTemplate restTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void submit() throws JsonProcessingException {
        SubmissionResult result = new SubmissionResult();
        result.setValid(false);
        when(checkerService.processResponse(any(), any(), any())).thenReturn(result);
        SubmissionRequest request = new SubmissionRequest();
        request.setSource("print 1");
        request.setTaskId(1);

        ProgressId progressId = new ProgressId();
        User user =  new User();
        user.setUsername("admin.user");
        progressId.setUser(user);
        Task task = new Task();
        task.setTaskId(1);
        progressId.setTask(task);

        restTemplate.postForObject("/checker/submission", request, SubmissionResult.class);
        result.setValid(true);
        request.setSource("correct");

        Progress progress0 = entityManager.createQuery("select p from Progress p  where p.pk=:id", Progress.class)
                .setParameter("id",progressId).getSingleResult();
        assertEquals(null, progress0.getSolution());
        assertEquals(null, progress0.getLanguage());
        assertEquals("1", progress0.getAttempts().toString());

        SubmissionResult rs = restTemplate.postForObject("/checker/submission", request, SubmissionResult.class);



        Progress progress = entityManager.createQuery("select p from Progress p  where p.pk=:id", Progress.class)
                .setParameter("id",progressId).getSingleResult();
        assertEquals("correct", progress.getSolution());
        assertEquals("2", progress.getAttempts().toString());
    }
}
