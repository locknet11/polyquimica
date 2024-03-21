package com.polyquimica.app.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polyquimica.app.domain.user.exception.UserException;
import com.polyquimica.app.domain.user.model.User;
import com.polyquimica.app.domain.user.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) throws UserException {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

}
