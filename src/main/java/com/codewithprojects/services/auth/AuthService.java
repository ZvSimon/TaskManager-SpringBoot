package com.codewithprojects.services.auth;

import com.codewithprojects.dto.SignupRequest;
import com.codewithprojects.dto.UserDTO;

public interface AuthService {

     UserDTO createUser(SignupRequest signupRequest) throws Exception;

     Boolean hasUserWithEmail(String email);

}
