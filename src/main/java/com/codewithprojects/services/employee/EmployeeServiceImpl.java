package com.codewithprojects.services.employee;

import com.codewithprojects.dto.CommentDTO;
import com.codewithprojects.dto.TaskDTO;
import com.codewithprojects.entity.Comment;
import com.codewithprojects.entity.Task;
import com.codewithprojects.entity.User;
import com.codewithprojects.enums.TaskStatus;
import com.codewithprojects.repository.CommentRepository;
import com.codewithprojects.repository.TaskRepository;
import com.codewithprojects.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final TaskRepository taskRepository;

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public List<TaskDTO> getTasksByUserId(Long id) {
        return taskRepository.findByUserId(id)
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Task updateTask(Long taskId, String status) {
        Optional<Task> optionalRoom = taskRepository.findById(taskId);
        if (optionalRoom.isPresent()) {
            Task existingTask = optionalRoom.get();
            TaskStatus newStatus = mapStringToTaskStatus(status);
            existingTask.setTaskStatus(newStatus);
            return taskRepository.save(existingTask);
        }
        return null;
    }

    private TaskStatus mapStringToTaskStatus(String statusString) {
        return switch (statusString) {
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDTO).orElse(null);
    }

    public Comment createComment(Long taskId, Long postedBy, String content){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<User> optionalUser = userRepository.findById(taskId);
        if(optionalTask.isPresent() && optionalUser.isPresent()){
            Comment comment = new Comment();

            comment.setTask(optionalTask.get());
            comment.setContent(content);
            comment.setPostedBy(optionalUser.get());
            comment.setCreatedAt(new Date());

            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Task not found");
    }

    public List<CommentDTO> getCommentsByTaskId(Long taskId){
        return commentRepository.findByTaskId(taskId).stream().map(Comment::getDto).collect(Collectors.toList());
    }
}
