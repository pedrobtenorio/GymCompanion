package com.pedro.GymCompanion.Controller;


import com.pedro.GymCompanion.Domain.AuthResponse;
import com.pedro.GymCompanion.Domain.User;
import com.pedro.GymCompanion.Security.AccessToken;
import com.pedro.GymCompanion.Security.Credential;
import com.pedro.GymCompanion.Service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/users")
public class UserController {
    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @ApiOperation(value = "retrieve Method, Return user by it id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "user returned successfully"),
            @ApiResponse(code = 404, message = "user not returned user not found by id"),

    })
    @GetMapping("/{id}")
    public User retrieve(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @ApiOperation(value = "create Method, Create user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created"),
    })
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return this.userService.save(user);
    }

    @ApiOperation(value = "authenticate Method, authenticate user token by comparing insert password with the database password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User authenticated successfully."),
            @ApiResponse(code = 401, message = "password dont match."),
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody Credential credential) {
        return this.userService.authenticate(credential);
    }
}
