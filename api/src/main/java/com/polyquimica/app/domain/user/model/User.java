package com.polyquimica.app.domain.user.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.polyquimica.app.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Document(collection = "users")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class User extends BaseEntity {

    @Field
    @Indexed(unique = true)
    private String email;

    @Field
    private String encodedPassword;

    @Field
    private String fullName;

    @Field
    private String cuit = "00-00000000-0";

    @Field
    private String company = "POLYQUIMICA";

    @Field
    private Role role;

    @Field
    private List<ModuleAccess> moduleAccess = new ArrayList<>();

}