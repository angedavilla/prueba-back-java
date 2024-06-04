package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.AuditLog;
import com.prueba.prueba.Entities.AuditLogEntities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends CrudRepository<AuditLog, Long> {
}
