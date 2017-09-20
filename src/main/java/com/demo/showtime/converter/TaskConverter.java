package com.demo.showtime.converter;

import com.demo.showtime.model.Task;
import com.demo.showtime.model.TaskReq;

/**
 * Created by atujicov on 9/20/2017.
 */
public final class TaskConverter {
    private TaskConverter() {
    }

    public static Task convertDTOToEntity(TaskReq taskReq) {
        Task task0 = new Task();
        task0.setTaskId(taskReq.getId());
        task0.setTitle(taskReq.getTitle());
        task0.setDescription(taskReq.getDescription());
        return task0;
    }
}
