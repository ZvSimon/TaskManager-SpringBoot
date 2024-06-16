package com.codewithprojects.entity;

import com.codewithprojects.dto.TaskDTO;
import com.codewithprojects.enums.PriorityStatus;
import com.codewithprojects.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    private TaskStatus taskStatus;


    public TaskDTO getTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setDueDate(dueDate);
        taskDTO.setPriority(priority);
        taskDTO.setTaskStatus(taskStatus);
        taskDTO.setEmployeeId(user.getId());
        taskDTO.setEmployeeName(user.getName());
        return taskDTO;
    }

}