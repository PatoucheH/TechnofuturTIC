package be.bstorm.tf_java2026_producthell.dl.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "user_")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "email", "username"}) @ToString(of = {"id","email","username"})
public class User implements UserDetails {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Getter @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
