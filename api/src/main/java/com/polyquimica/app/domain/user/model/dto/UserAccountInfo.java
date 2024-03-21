package com.polyquimica.app.domain.user.model.dto;

import java.util.List;

import com.polyquimica.app.domain.user.model.ModuleAccess;
import com.polyquimica.app.domain.user.model.Role;
import lombok.Data;

@Data
public class UserAccountInfo {
    String id;
    String email;
    String fullName;
    String company;
    String cuit;
    Role role;
    List<ModuleAccess> moduleAccess;
}
