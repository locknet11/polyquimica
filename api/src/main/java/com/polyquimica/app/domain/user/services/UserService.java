package com.polyquimica.app.domain.user.services;

import com.polyquimica.app.domain.user.exception.UserException;
import com.polyquimica.app.domain.user.model.User;

public interface UserService {
    User createUser(User user) throws UserException;

    User getById(String id) throws UserException;

    User getByUsername(String username) throws UserException;

    User getCurrentUser(String email);

    boolean emailExists(String email);
}
