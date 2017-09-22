package com.demo.showtime.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by atujicov on 9/19/2017.
 */
@Entity
@Table(name = "TEST_CASES", uniqueConstraints = @UniqueConstraint(columnNames = {"TASK_ID", "INPUT"}, name = "uk_task_input"))
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    @Column(name = "TEST_CASE_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "TASK_ID", foreignKey = @ForeignKey(name = "fk_task"))
    private Task task;

    public TestCase() {
    }

    public TestCase(Task task, String input, String output) {
        this.task = task;
        this.input = input;
        this.output = output;
    }
    @NotNull
    private String input;
    @NotNull
    private String output;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        if (!getTask().equals(testCase.getTask())) return false;
        if (getInput() != null ? !getInput().equals(testCase.getInput()) : testCase.getInput() != null) return false;
        return getOutput() != null ? getOutput().equals(testCase.getOutput()) : testCase.getOutput() == null;
    }

    @Override
    public int hashCode() {
        int result = getTask().hashCode();
        result = 31 * result + (getInput() != null ? getInput().hashCode() : 0);
        result = 31 * result + (getOutput() != null ? getOutput().hashCode() : 0);
        return result;
    }
}
