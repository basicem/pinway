package com.example.userservice.services;



import com.example.userservice.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    public abstract Role addRole(Role role);
    public abstract Role updateRole(Integer id, Role role);
    public abstract void deleteRole(Integer id);
    public abstract Role getRole(String name);
    public abstract Role getRole(Integer id);
}
