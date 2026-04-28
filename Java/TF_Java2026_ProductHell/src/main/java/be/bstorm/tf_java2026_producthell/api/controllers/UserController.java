package be.bstorm.tf_java2026_producthell.api.controllers;

import be.bstorm.tf_java2026_producthell.api.models.responses.UserResponse;
import be.bstorm.tf_java2026_producthell.bll.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(userService.get());
    }
}
