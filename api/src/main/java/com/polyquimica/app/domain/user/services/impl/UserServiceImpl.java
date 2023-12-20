package com.polyquimica.app.domain.user.services.impl;

import java.time.LocalDateTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.polyquimica.app.domain.user.db.UserRepository;
import com.polyquimica.app.domain.user.exception.UserException;
import com.polyquimica.app.domain.user.model.User;
import com.polyquimica.app.domain.user.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User createUser() {
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail("test@test.com");
        return repository.save(user);
    }

    @Override
    public User getById(String id) throws UserException {
        return repository.findById(id).orElseThrow(() -> new UserException("user.error.notfound"));
    }

    @Override
    public User getByUsername(String username) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByUsername'");
    }

}
