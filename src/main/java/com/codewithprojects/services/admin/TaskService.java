package com.codewithprojects.services.admin;


import com.codewithprojects.dto.CommentDTO;
import com.codewithprojects.dto.TaskDTO;
import com.codewithprojects.dto.UserDTO;
import com.codewithprojects.entity.Comment;
import com.codewithprojects.entity.Task;

import java.util.List;

public interface TaskService {

    Task postTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    Task updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    List<UserDTO> getAllUsers();

    List<TaskDTO> searchTasks(String title);

    Comment createComment(Long taskId, Long postedBy, String content);

    List<CommentDTO> getCommentsByTaskId(Long taskId);
}
