package com.demo.showtime.dao;

import com.demo.showtime.model.Progress;
import com.demo.showtime.model.ProgressId;
import com.demo.showtime.model.Task;
import com.demo.showtime.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by atujicov on 9/14/2017.
 */
public interface ProgressRepository extends PagingAndSortingRepository<Progress, ProgressId> {
//    @Query("SELECT p FROM Progress p WHERE p.task.taskId=:taskId AND p.user.username=:userId")
//    Progress findByTaskAndUser(Integer taskId, String userId);
}
