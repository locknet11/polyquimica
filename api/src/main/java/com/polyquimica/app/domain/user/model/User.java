package com.polyquimica.app.domain.user.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.polyquimica.app.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    @Field
    @Indexed(unique = true)
    private String email;

    @Field
    private Role role;

    @Field
    private List<ModuleAccess> moduleAccess = new ArrayList<>();
    
}