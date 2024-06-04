package com.prueba.prueba.Listener;

import com.prueba.prueba.Entities.AuditLog;
import com.prueba.prueba.Repository.AuditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditEntityListener {

    private static AuditRepository auditRepository;
    @Autowired
    public void init(AuditRepository auditRepository) {
        AuditEntityListener.auditRepository = auditRepository;
    }

    @PrePersist
    public void prePersist(Object object) {
        createAuditLog(object, "INSERT");
    }

    @PreUpdate
    public void preUpdate(Object object) {
        createAuditLog(object, "UPDATE");
    }

    @PreRemove
    public void preRemove(Object object) {
        createAuditLog(object, "DELETE");
    }

    private void createAuditLog(Object entity, String action) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entity.getClass().getSimpleName());
        auditLog.setAction(action);
        auditLog.setPerformedBy(getUsername());
        auditLog.setTimestamp(LocalDateTime.now());
        auditLog.setNewValue(getNewValue(entity));
        String username = getUsername();
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo o vacío.");
        }
        auditLog.setUsername(username);

        auditRepository.save(auditLog);
    }


    private String getNewValue(Object entity) {
        return convertEntityToString(entity);
    }

    private String convertEntityToString(Object entity) {
        Map<String, Object> entityMap = new HashMap<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                entityMap.put(field.getName(), field.get(entity));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("No se pudo acceder al campo de la entidad", e);
            }
        }
        return entityMap.toString();
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "System";
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }


}
