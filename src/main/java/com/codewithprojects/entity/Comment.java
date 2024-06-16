package com.codewithprojects.entity;

import com.codewithprojects.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date createdAt;

    @ManyToOne
    private User postedBy;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public CommentDTO getDto(){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setContent(content);
        commentDTO.setCreatedAt(createdAt);
        commentDTO.setPostedBy(postedBy.getName());
        return commentDTO;
    }
}
