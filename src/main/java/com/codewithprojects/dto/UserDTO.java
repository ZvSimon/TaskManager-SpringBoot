package com.codewithprojects.dto;

import com.codewithprojects.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
