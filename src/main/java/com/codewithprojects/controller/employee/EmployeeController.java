package com.codewithprojects.controller.employee;

import com.codewithprojects.dto.TaskDTO;
import com.codewithprojects.entity.Task;
import com.codewithprojects.services.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTasksByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getTasksByUserId(id));
    }

    @GetMapping("/task/{taskId}/{status}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @PathVariable String status) {
        Task updatedTask = employeeService.updateTask(taskId, status);
        if (updatedTask != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/comments/create")
    public ResponseEntity<?> createComment(@RequestParam Long taskId, @RequestParam Long postedBy, @RequestBody String content){
        try {
            return ResponseEntity.ok(employeeService.createComment(taskId, postedBy, content));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getTaskById(id));
    }

    @GetMapping("/comments/{taskId}")
    public ResponseEntity<?> getCommentsByTaskId(@PathVariable Long taskId){
        try {
            return ResponseEntity.ok(employeeService.getCommentsByTaskId(taskId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }
    }

}
