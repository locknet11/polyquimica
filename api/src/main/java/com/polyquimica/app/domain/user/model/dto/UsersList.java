package com.polyquimica.app.domain.user.model.dto;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.polyquimica.app.domain.user.model.User;

public class UsersList extends PageImpl<User> {

    public UsersList(List<User> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
