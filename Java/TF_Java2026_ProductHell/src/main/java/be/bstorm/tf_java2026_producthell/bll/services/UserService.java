package be.bstorm.tf_java2026_producthell.bll.services;

import be.bstorm.tf_java2026_producthell.api.models.responses.UserResponse;
import be.bstorm.tf_java2026_producthell.dal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> get(){
        return userRepository.findAll().stream()
                .map(UserResponse::fromUser)
                .toList();
    }
}
