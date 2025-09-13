package com.svick.brz.spring.controller;

import com.svick.brz.spring.model.dto.UserDto;
import com.svick.brz.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> users(@RequestParam(required = false, value = "q") String query, Pageable pageable) {
        return ResponseEntity.ok(userService.users(query, pageable));
    }
}
