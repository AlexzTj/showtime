package com.demo.showtime.model;

import com.demo.showtime.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by atujicov on 9/14/2017.
 */
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, name = "TASK_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Integer taskId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "categoryId",
            resolver = EntityIdResolver.class,
            scope = Category.class)
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @JsonIgnore
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY)
//
//    @JoinColumn(name = "USERNAME", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    @NotEmpty
    @Length(min = 1, max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Length(max = 100000)
    private String description;

    @UpdateTimestamp
    @Column(name = "last_modified")
    @JsonIgnore
    private Date lastModified;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    @JsonIgnore
    private Date createdOn;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Valid
    @NotEmpty
    private Set<TestCase> testCases = new HashSet<>(0);

    @OneToMany(mappedBy = "pk.task", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Progress> progressSet = new HashSet<>(0);

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Set<Progress> getProgressSet() {
        return progressSet;
    }

    public void setProgressSet(Set<Progress> progressSet) {
        this.progressSet = progressSet;
    }


    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Set<TestCase> testCases) {
        this.testCases = testCases;
    }

    public void addTestCase(TestCase testCase) {
        testCase.setTask(this);
        testCases.add(testCase);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (getTaskId() != null ? !getTaskId().equals(task.getTaskId()) : task.getTaskId() != null) return false;
        if (!getCategory().equals(task.getCategory())) return false;
       // if (!getUser().equals(task.getUser())) return false;
        if (getTitle() != null ? !getTitle().equals(task.getTitle()) : task.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(task.getDescription()) : task.getDescription() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(task.getLastModified()) : task.getLastModified() != null)
            return false;
        return getCreatedOn() != null ? getCreatedOn().equals(task.getCreatedOn()) : task.getCreatedOn() == null;
    }

    @Override
    public int hashCode() {
        int result = getTaskId() != null ? getTaskId().hashCode() : 0;
        result = 31 * result + getCategory().hashCode();
//        result = 31 * result + getUser().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getCreatedOn() != null ? getCreatedOn().hashCode() : 0);
        return result;
    }
}
