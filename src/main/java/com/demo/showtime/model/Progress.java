package com.demo.showtime.model;

import com.demo.showtime.validator.NullOrNotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by atujicov on 9/18/2017.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"TASK_ID", "USER_ID"}, name = "uk_task_user"))
public class Progress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    @Access(AccessType.PROPERTY)
    private Integer progressId;
    @NullOrNotBlank
    private String solution;
    @NullOrNotBlank
    private Integer language;
    @NotNull
    private Integer attempts;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TASK_ID", foreignKey = @ForeignKey(name = "fk_task"))
    private Task task;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;


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

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

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

        if (!getLanguage().equals(progress.getLanguage())) return false;
        if (!getTask().equals(progress.getTask())) return false;
        return getUser().equals(progress.getUser());
    }

    @Override
    public int hashCode() {
        int result = getLanguage().hashCode();
        result = 31 * result + getTask().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}
