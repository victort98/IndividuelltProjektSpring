package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(String username) {
        if(username != null) {
            var user = userRepository.findByUsername(username).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Could not find the user by username %s.", username)));
            return List.of(user);
        }
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the user by id %s.", id)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    public User save(User user) {
        if(StringUtils.isEmpty(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I need a password");
        }
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        return userRepository.save(user);
    }

    public void update(String id, User user) {
        var currentUser = findByUsername(myUserDetailsService.getCurrentUser());
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s.", id));
        }
        if(sameUserOrAdmin(currentUser, id)) {
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You can not update this user");
    }

    public void delete(String id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s.", id));
        }
        userRepository.deleteById(id);
    }

    private Boolean sameUserOrAdmin (User currentUser, String id) {
        return (currentUser.getId().equals(id) || currentUser.getRoles().contains("ADMIN"));
    }
}
