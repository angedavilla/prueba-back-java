package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.RolesEntities;
import com.prueba.prueba.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RolesRepository rolesRepository;

    @Transactional(readOnly = true)
    public List<RolesEntities> getAllRoles() {
        List<RolesEntities> roleList = (List<RolesEntities>) rolesRepository.findAll();
        if (roleList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No roles found");
        }
        return roleList;
    }

    @Transactional(readOnly = true)
    public RolesEntities getRoleId(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<RolesEntities> getRole(Long userId) {
        return rolesRepository.findById(userId);
    }

    @Transactional
    public RolesEntities saveRole(RolesEntities rolesEntities) {
        return rolesRepository.save(rolesEntities);
    }

    @Transactional
    public RolesEntities updateRole(Long id, RolesEntities rolesEntities) {
        RolesEntities existRole = rolesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with ID: " + id));

        existRole.setName(rolesEntities.getName());

        return rolesRepository.save(existRole);
    }

    @Transactional
    public void deleteRole(Long id) {
        if (!rolesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with ID: " + id);
        }
        rolesRepository.deleteById(id);
    }

}
