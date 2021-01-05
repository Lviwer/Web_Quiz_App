package engine.controllers;

import engine.entity.Role;
import engine.entity.User;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody User user) {
        try {
            user.setActive(true);
            user.setRoles(Collections.singletonList(Role.USER));
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
