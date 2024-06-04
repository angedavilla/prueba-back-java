package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.UserEntities;
import com.prueba.prueba.Repository.UserRepository;
import com.prueba.prueba.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/list")
    public ResponseEntity<List<UserEntities>> getAllUsers(){
        List<UserEntities> userEntities = userService.getAllUsers();
        return ResponseEntity.ok(userEntities);
    }

    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username) {
        return userService.getRolesByUsername(username);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntities> getUserId(@PathVariable Long id){
        UserEntities userEntities = userService.getUserById(id);
        return ResponseEntity.ok(userEntities);
    }

    @PostMapping("/{username}/roles/{roleName}")
    public void addRoleToUser(@PathVariable String username, @PathVariable String roleName) {
        userService.addRoleToUser(username, roleName);
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserEntities> registerUser(@RequestBody UserEntities userEntities){
        UserEntities newUserEntities = userService.saveUser(userEntities);
        return ResponseEntity.ok(newUserEntities);
    }

    @PutMapping("/{username}/roles")
    public ResponseEntity<?> changeUserRole(@PathVariable String username,
                                            @RequestParam String oldRoleName,
                                            @RequestParam String newRoleName) {
        try {
            userService.updateRoleOfUser(username, oldRoleName, newRoleName);
            return ResponseEntity.ok("El rol del usuario se ha cambiado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Se produjo un error al cambiar el rol del usuario: " + e.getMessage());
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserEntities> updateUser(@PathVariable Long id, @RequestBody UserEntities userEntities){
        UserEntities updateUser = userService.updateUser(id, userEntities);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}/roles/{roleName}")
    public void removeRoleFromUser(@PathVariable String username, @PathVariable String roleName) {
        userService.removeRoleFromUser(username, roleName);
    }


}
