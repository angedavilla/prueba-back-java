package com.prueba.prueba.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersRoles {

    @EmbeddedId
    private UsersRolesId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntities user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private RolesEntities role;

    public UsersRolesId getId() {
        return id;
    }

    public void setId(UsersRolesId id) {
        this.id = id;
    }

    public UserEntities getUser() {
        return user;
    }

    public void setUser(UserEntities user) {
        this.user = user;
    }

    public RolesEntities getRole() {
        return role;
    }

    public void setRole(RolesEntities role) {
        this.role = role;
    }
}
