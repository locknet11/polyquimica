package com.polyquimica.app.domain.user.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.polyquimica.app.domain.user.model.User;

public interface UserRepository extends MongoRepository<User, String>{
    
}
