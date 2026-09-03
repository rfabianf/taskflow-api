package com.fabian.taskflow_api.controller;


import com.fabian.taskflow_api.dto.response.UserResponse;
import com.fabian.taskflow_api.service.Interfaces.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
//@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    List<UserResponse> GetAllUsers()
   {
       return userService.getAllUsers();
   }
}
