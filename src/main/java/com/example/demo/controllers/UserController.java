package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @Operation(summary = "Find users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the user/s"),
            @ApiResponse(responseCode = "400", description = "Invalid", content = @Content)
    })
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String username) {
        var users = userService.findAll(username);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the user"),
            @ApiResponse(responseCode = "404", description = "No user found with that id.", content = @Content)
    })
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Save user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added user."),
            @ApiResponse(responseCode = "401", description = "Only admins can add users.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid.", content = @Content)
    })
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_EDITOR", "ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated user."),
            @ApiResponse(responseCode = "401", description = "Only admins can update users.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT) // status code 204

    public void updateUser(@PathVariable String id, @Validated @RequestBody User user) {
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Delete pokemon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted user."),
            @ApiResponse(responseCode = "401", description = "Only admins can delete users.", content = @Content),
            @ApiResponse(responseCode = "404", description = "No user found.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT) // status code 204
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}
