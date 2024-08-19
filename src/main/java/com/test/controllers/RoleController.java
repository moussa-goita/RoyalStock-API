package com.test.controllers;

import com.test.entities.Role;
import com.test.repositories.RoleRepository;
import com.test.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        return roleService.findById(id)
                .map(role -> ResponseEntity.ok().body(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role roleDetails) {
        return roleService.findById(id)
                .map(role -> {
                    role.setName(roleDetails.getName());
                    Role updatedRole = roleService.save(role);
                    return ResponseEntity.ok().body(updatedRole);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
