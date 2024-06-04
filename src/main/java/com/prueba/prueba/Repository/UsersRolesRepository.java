package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.UsersRoles;
import com.prueba.prueba.Entities.UsersRolesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {

    Optional<UsersRoles> findByUserIdAndRoleId(Long userId, Long roleId);
    List<UsersRoles> findByUserId(Long userId);

}
