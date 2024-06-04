package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.RolesEntities;
import com.prueba.prueba.Entities.UserEntities;
import com.prueba.prueba.Entities.UsersRoles;
import com.prueba.prueba.Entities.UsersRolesId;
import com.prueba.prueba.Repository.RolesRepository;
import com.prueba.prueba.Repository.UserRepository;
import com.prueba.prueba.Repository.UsersRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Transactional(readOnly = true)
    public List<UserEntities> getAllUsers() {
        List<UserEntities> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        }
        return userList;
    }

    @Transactional
    public List<String> getRolesByUsername(String username) {
        UserEntities user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<UsersRoles> userRoles = usersRolesRepository.findByUserId(user.getId());
        return userRoles.stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toList());
    }

    public void addRoleToUser(String username, String roleName) {
        UserEntities user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        RolesEntities role = rolesRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UsersRolesId userRoleId = new UsersRolesId();
        userRoleId.setUserId(user.getId());
        userRoleId.setRoleId(((RolesEntities) role).getId());

        UsersRoles userRole = new UsersRoles();
        userRole.setId(userRoleId);
        userRole.setUser(user);
        userRole.setRole((RolesEntities) role);

        usersRolesRepository.save(userRole);
    }


    @Transactional
    public void updateRoleOfUser(String username, String oldRoleName, String newRoleName) {
        UserEntities user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        RolesEntities oldRole = rolesRepository.findByName(oldRoleName)
                .orElseThrow(() -> new RuntimeException("Old role not found"));

        RolesEntities newRole = rolesRepository.findByName(newRoleName)
                .orElseThrow(() -> new RuntimeException("New role not found"));

        UsersRoles userRole = usersRolesRepository.findByUserIdAndRoleId(user.getId(), oldRole.getId())
                .orElseThrow(() -> new RuntimeException("UserRole not found"));

        usersRolesRepository.delete(userRole);
        UsersRoles newUserRole = new UsersRoles(new UsersRolesId(user.getId(), newRole.getId()), user, newRole);
        usersRolesRepository.save(newUserRole);
    }


    @Transactional(readOnly = true)
    public UserEntities getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id));
    }

    @Transactional
    public UserEntities saveUser(UserEntities userEntities) {
        return userRepository.save(userEntities);
    }

    @Transactional
    public UserEntities updateUser(Long id, UserEntities userEntities) {
        UserEntities existUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id));

        existUser.setUsername(userEntities.getUsername());
        existUser.setPassword(userEntities.getPassword());
        existUser.setEmail(userEntities.getEmail());
        existUser.setRoles(userEntities.getRoles());

        return userRepository.save(existUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void removeRoleFromUser(String username, String roleName) {
        UserEntities user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        RolesEntities role = rolesRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UsersRoles userRole = usersRolesRepository.findByUserIdAndRoleId(user.getId(), role.getId())
                .orElseThrow(() -> new RuntimeException("UserRole not found"));

        usersRolesRepository.delete(userRole);
    }
}
