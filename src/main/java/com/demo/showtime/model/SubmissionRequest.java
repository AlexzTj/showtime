package com.demo.showtime.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by atujicov on 9/20/2017.
 */
public class SubmissionRequest {
    @NotNull
    private Integer taskId;
    @NotEmpty
    private String source;

    public Integer getTaskId() {
        return taskId;
    }

    public String getSource() {
        return source;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
