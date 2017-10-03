package com.demo.showtime.model;

import com.demo.showtime.validator.NullOrNotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by atujicov on 9/18/2017.
 */
@Entity
@AssociationOverrides({
        @AssociationOverride(name = "pk.task",
                joinColumns = @JoinColumn(name = "TASK_ID")),
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "USER_ID"))})
public class Progress implements Serializable {
    @EmbeddedId
    private ProgressId pk = new ProgressId();
    @Size(max = 2000)
    private String solution;

    private Integer language;

    private Integer attempts = 0;

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public ProgressId getPk() {
        return pk;
    }

    public void setPk(ProgressId pk) {
        this.pk = pk;
    }

    @Transient
    public Task getTask() {
        return pk.getTask();
    }

    public void setTask(Task task) {
        pk.setTask(task);
    }

    @Transient
    public User getUser() {
        return pk.getUser();
    }

    public void setUser(User user) {
        pk.setUser(user);
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Progress progress = (Progress) o;

        return getPk().equals(progress.getPk());
    }

    @Override
    public int hashCode() {
        return getPk().hashCode();
    }
}
