package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.UsersRoles;
import com.prueba.prueba.Entities.UsersRolesId;
import com.prueba.prueba.Repository.RolesRepository;
import com.prueba.prueba.Repository.UsersRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsersRolesService {
    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public void updateRole(Long userId, Long roleId) {
        Optional<UsersRoles> optionalUsersRoles = usersRolesRepository.findByUserIdAndRoleId(userId, roleId);
        if (optionalUsersRoles.isPresent()) {
            UsersRoles usersRoles = optionalUsersRoles.get();
            usersRoles.setRole(rolesRepository.findById(roleId).orElseThrow());
            usersRolesRepository.save(usersRoles);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UsersRoles not found");
        }
    }
}