package com.example.productsdelivery.controllers;

import com.example.productsdelivery.model.RoleModel;
import com.example.productsdelivery.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleRepo roleRepo;

    @GetMapping
    @PreAuthorize("hasAuthority('3')")
    public List<RoleModel> getRoleList() {
        return roleRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('3')")
    public Optional<RoleModel> getRole(@PathVariable String id) {
        return roleRepo.findById(Integer.valueOf(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('3')")
    public RoleModel createRole(@RequestBody RoleModel role) {
        RoleModel newRole = roleRepo.save(role);
        return newRole;
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('3')")
    public RoleModel updateRole(@PathVariable String id, @RequestBody RoleModel role) {
        RoleModel newRole = roleRepo.findById(Integer.valueOf(id)).orElse(null);
        if(newRole == null) return null;
        if (role.getName() != null) {
            newRole.setName(role.getName());
        }
        newRole.setId(Integer.valueOf(id));
        roleRepo.save(newRole);
        return newRole;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('3')")
    public void deleteRole(@PathVariable String id) {
        roleRepo.deleteById(Integer.valueOf(id));
    }
}
