package com.codewithprojects.services.employee;


import com.codewithprojects.dto.CommentDTO;
import com.codewithprojects.dto.TaskDTO;
import com.codewithprojects.entity.Comment;
import com.codewithprojects.entity.Task;

import java.util.List;

public interface EmployeeService {


    List<TaskDTO> getTasksByUserId(Long id);

    Task updateTask(Long taskId, String status);

    TaskDTO getTaskById(Long id);

    Comment createComment(Long taskId, Long postedBy, String content);

    List<CommentDTO> getCommentsByTaskId(Long taskId);
}
