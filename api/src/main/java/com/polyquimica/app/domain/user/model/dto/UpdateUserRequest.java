package com.polyquimica.app.domain.user.model.dto;

import java.util.List;
import com.polyquimica.app.domain.user.model.ModuleAccess;
import com.polyquimica.app.domain.user.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    String email;
    String fullName;
    Role role;
    List<ModuleAccess> moduleAccess;
}
