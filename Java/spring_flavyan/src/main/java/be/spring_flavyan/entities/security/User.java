package be.spring_flavyan.entities.security;

import be.spring_flavyan.entities.base.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity<Long> implements UserDetails {

    private String email;
    private String password;

    @Override  public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public @Nullable String getUsername() {
        return this.email;
    }


}
