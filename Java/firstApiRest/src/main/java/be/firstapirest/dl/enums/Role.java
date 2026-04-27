package be.firstapirest.dl.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}
