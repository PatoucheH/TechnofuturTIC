package be.bstorm.tf_java2026_producthell.api.models.responses;

import be.bstorm.tf_java2026_producthell.dl.entities.User;

public record UserResponse(
        Integer id,
        String email,
        String username,
        String password
) {

    public static UserResponse fromUser(User u){
        return new UserResponse(
                u.getId(),
                u.getEmail(),
                u.getUsername(),
                u.getPassword()
        );
    }
}
