package com.polyquimica.app.domain.user.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.polyquimica.app.domain.user.exception.UserException;
import com.polyquimica.app.domain.user.model.User;
import com.polyquimica.app.domain.user.model.dto.CreateUserRequest;
import com.polyquimica.app.domain.user.model.dto.UpdateUserRequest;
import com.polyquimica.app.domain.user.model.dto.UserAccountInfo;
import com.polyquimica.app.domain.user.services.UserService;
import com.polyquimica.app.domain.user.services.UserServiceMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserServiceMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<UserAccountInfo>> getUsers(
        @RequestParam(name = "params", required = false) Map<String, Object> params) {
            
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(userMapper.userListToUserAccountInfoList(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountInfo> getUserById(@PathVariable("id") String id) throws UserException {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(userMapper.userToUserAccountInfo(user));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request)
     throws URISyntaxException, UserException{
        User created = userService.createUser(userMapper.createUserRequestToUser(request));
        UserAccountInfo dto = userMapper.userToUserAccountInfo(created);
        return ResponseEntity.created(new URI(String.format("/user/%s", dto.getId()))).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccountInfo> updateUserById(@PathVariable("id") String userId, 
    @RequestBody @Valid UpdateUserRequest request) throws UserException{
        User updatedUser = userMapper.updateUserRequestToUser(request, userId);
        userService.updateUser(updatedUser);
        UserAccountInfo dto = userMapper.userToUserAccountInfo(updatedUser);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
