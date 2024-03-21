package com.polyquimica.app.domain.user.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.polyquimica.app.domain.user.exception.UserException;
import com.polyquimica.app.domain.user.model.Role;
import com.polyquimica.app.domain.user.model.User;
import com.polyquimica.app.domain.user.model.dto.CreateClientUserRequest;
import com.polyquimica.app.domain.user.model.dto.CreateUserRequest;
import com.polyquimica.app.domain.user.model.dto.UserAccountInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserServiceMapper {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public User createUserRequestToUser(CreateUserRequest request) throws UserException {
        if (emailExists(request.getEmail())) {
            throw new UserException("user.email.alreadyExists");
        }
        User user = new User();
        user.setEmail(request.getEmail())
                .setFullName(request.getFullName())
                .setEncodedPassword(passwordEncoder.encode(request.getPassword()))
                .setRole(Role.USER)
                .setCreatedAt(LocalDateTime.now());
        return user;
    }

    public User createClientUserRequestToUser(CreateClientUserRequest request) throws UserException {
        if (emailExists(request.getEmail())) {
            throw new UserException("user.email.alreadyExists");
        }
        User user = new User();
        user.setEmail(request.getEmail())
                .setFullName(request.getFullName())
                .setEncodedPassword(passwordEncoder.encode(request.getPassword()))
                .setRole(Role.CLIENT)
                .setCuit(request.getCuit())
                .setCompany(getRequestCompany(request.getCompany()))
                .setCreatedAt(LocalDateTime.now());
        return user;
    }

    public UserAccountInfo userToUserAccountInfo(User user) {
        return modelMapper.map(user, UserAccountInfo.class);
    }

    private boolean emailExists(String email) {
        return userService.emailExists(email);
    }

    private String getRequestCompany(String company) {
        if (company.equalsIgnoreCase("POLIQUIMICA")) {
            return "UNASSIGNED";
        }

        return company;
    }
}
