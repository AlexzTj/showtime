package com.demo.showtime.controller;

import com.demo.showtime.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by atujicov on 9/19/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:testdata.sql")
public class TaskRestControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void getAllTasks() {
        Map result = restTemplate.getForObject("/rest/tasks?page=0&size=1", LinkedHashMap.class);
        ArrayList content = (ArrayList) result.get("content");
        assertEquals(1, content.size());
    }

    @Test
    public void getTaskById() throws Exception {
        Task result = restTemplate.getForObject("/rest/tasks/{id}", Task.class, 1);
        assertTrue(result.getProgressSet().isEmpty());
        assertTrue(result.getTestCases().isEmpty());
        assertEquals(new Integer(1), result.getTaskId());
    }

    @Test
    public void createTask() throws Exception {
        TaskReq task = new TaskReq();

        task.setCategory(1);
        Set<TestCaseDTO> testCases = new HashSet<>();
        TestCaseDTO testCase = new TestCaseDTO();
        testCase.setInput("1");
        testCase.setOutput("2");
        testCases.add(testCase);
        task.setTestCases(testCases);
        task.setDescription("custom desc");
        task.setTitle("custom task");
        ResponseEntity result = restTemplate.postForEntity("/rest/tasks", task, ResponseEntity.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateTask() throws Exception {
        TaskReq task = new TaskReq();
        task.setId(1);
        task.setCategory(2);
        Set<TestCaseDTO> testCases = new HashSet<>();
        TestCaseDTO testCase = new TestCaseDTO();
        testCase.setId(1);
        testCase.setInput("test1");
        testCase.setOutput("test2");
        testCases.add(testCase);
        task.setTestCases(testCases);
        task.setDescription("custom desc");
        task.setTitle("custom task");
        ResponseEntity result = restTemplate.postForEntity("/rest/tasks", task, ResponseEntity.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        Task task1 = entityManager.createQuery("select task from Task task join fetch task.testCases where task.taskId=:id", Task.class)
                .setParameter("id", 1).getSingleResult();
        assertEquals("2", task1.getCategory().getCategoryId().toString());
        Set<TestCase> testCases1 = task1.getTestCases();
        assertEquals("custom desc", task1.getDescription());
        assertNotNull(task1.getCreatedOn());
        assertNotNull(task1.getLastModified());
        assertEquals(1, testCases1.size());
        for (TestCase ts : testCases1) {
            assertEquals("test1", ts.getInput());
        }
    }

    @Test
    public void deleteTask() throws Exception {
        restTemplate.delete("/tasks/{id}", 1);
        List<Task> resultList = entityManager.createQuery("select task from Task task where task.taskId=:id", Task.class)
                .setParameter("id", 1).getResultList();
        assertTrue(resultList.isEmpty());
        List<TestCase> resultList0 = entityManager.createQuery("select t from TestCase t where t.task.taskId=:id", TestCase.class)
                .setParameter("id", 1).getResultList();
        assertTrue(resultList0.isEmpty());
        List<Progress> resultList1 = entityManager.createQuery("select pg from Progress pg where pg.task.taskId=:id", Progress.class)
                .setParameter("id", 1).getResultList();
        assertTrue(resultList1.isEmpty());
    }

}