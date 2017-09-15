package com.demo.showtime.dao;

import com.demo.showtime.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by atujicov on 9/14/2017.
 */
public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
