package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.RolesEntities;
import com.prueba.prueba.Service.RoleService;
import com.prueba.prueba.Service.UsersRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UsersRolesService usersRolesService;



    @GetMapping("/role/list")
    public ResponseEntity<List<RolesEntities>> getAllRoles(){
        List<RolesEntities> allRoles = roleService.getAllRoles();
        return ResponseEntity.ok(allRoles);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<RolesEntities> getRoleId(@PathVariable Long id){
        RolesEntities roleId = roleService.getRoleId(id);
        return ResponseEntity.ok(roleId);
    }

    @PostMapping("/role/register")
    public ResponseEntity<RolesEntities> saveRole(@RequestBody RolesEntities rolesEntities){
        RolesEntities newRole = roleService.saveRole(rolesEntities);
        return ResponseEntity.ok(newRole);
    }

    @PutMapping("/role/update/{id}")
    public ResponseEntity<RolesEntities> updateRole(@PathVariable Long id, @RequestBody RolesEntities rolesEntities){
        RolesEntities updateRole = roleService.updateRole(id, rolesEntities);
        return ResponseEntity.ok(updateRole);
    }

    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/role")
    public ResponseEntity<String> getRoleName(@PathVariable Long userId) {
        Optional<RolesEntities> role = roleService.getRole(userId);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get().getName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> updateRole(@PathVariable Long userId, @PathVariable Long roleId) {
        try {
            usersRolesService.updateRole(userId, roleId);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating role");
        }
    }

}
