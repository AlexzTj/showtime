package com.demo.showtime.dao;

import com.demo.showtime.model.Task;
import com.demo.showtime.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by atujicov on 9/14/2017.
 */
public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {
    List<TestCase> findTestCaseByTask(Task task);

}
