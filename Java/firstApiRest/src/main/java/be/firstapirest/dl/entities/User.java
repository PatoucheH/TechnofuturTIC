package be.firstapirest.dl.entities;

import be.firstapirest.dl.entities.base.BaseEntity;
import be.firstapirest.dl.entities.base.BaseEntityUUID;
import be.firstapirest.dl.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "t_user")
@NoArgsConstructor @AllArgsConstructor
@Data
public class User extends BaseEntityUUID {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
