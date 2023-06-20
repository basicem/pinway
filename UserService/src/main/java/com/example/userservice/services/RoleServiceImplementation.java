package com.example.userservice.services;


import com.example.userservice.models.Role;
import com.example.userservice.repositories.RoleRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role addRole(Role role) {
        Role _role = roleRepository.save(new Role(role.getName()));
        return _role;
    }

    @Override
    public Role updateRole(Integer id, Role role) {
        Role _role = roleRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Role with id = " + id + " does not exist!"));
        _role.setName(role.getName());
        return roleRepository.save(_role);
    }

    @Override
    public void deleteRole(Integer id) {
        if(id == null) {
            roleRepository.deleteAll();
            return;
        }
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role with id = " + id + " does not exist!");
        }
        roleRepository.deleteById(id);
        return;
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role getRole(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role with id = " + id + " does not exist!"));
        return role;
    }

}
