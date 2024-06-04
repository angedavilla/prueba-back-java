package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.RolesEntities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends CrudRepository<RolesEntities, Long> {

    Optional<RolesEntities> findByName(String name);

}
