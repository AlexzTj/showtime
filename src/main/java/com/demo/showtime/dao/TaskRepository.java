package com.demo.showtime.dao;

import com.demo.showtime.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by atujicov on 9/14/2017.
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {
}
