package com.demo.showtime.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by atujicov on 9/22/2017.
 */
@Embeddable
public class ProgressId implements Serializable {
    @ManyToOne
    private Task task;
    @ManyToOne
    private User user;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
