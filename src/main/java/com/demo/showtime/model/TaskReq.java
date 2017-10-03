package com.demo.showtime.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by atujicov on 9/20/2017.
 */
public class TaskReq {
    private Integer id;
    private Integer category;
    private String title;
    private String description;
    private Set<TestCaseDTO> testCases = new HashSet<>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TestCaseDTO> getTestCases() {
        return testCases;
    }

    public void setTestCases(Set<TestCaseDTO> testCases) {
        this.testCases = testCases;
    }
}
