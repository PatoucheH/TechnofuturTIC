package be.firstapirest.dal.enums;

public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
