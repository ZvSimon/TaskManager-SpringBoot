package com.codewithprojects.repository;

import com.codewithprojects.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List <Task> findByProjectName (String projectName);
    List<Task> findByUserId(Long id);

    List<Task> findAllByTitleContaining(String title);
}
