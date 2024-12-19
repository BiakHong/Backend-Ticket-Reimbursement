package com.revature.ticket_backend.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ticket_backend.Service.UserService;
import com.revature.ticket_backend.model.User;



@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;


    
    // @PostMapping("/register")
    // public ResponseEntity<?> registerUser(@RequestBody User user) {
    //     //check if username or email is taken
    //     if(userService.usernameExists(user.getUsername())){
    //         return ResponseEntity.badRequest().body("Username is already taken.");
    //     }
    //     if(userService.emailExists(user.getEmail())){
    //         return ResponseEntity.badRequest().body("Email is already taken");
    //     }
        
    //     User newUser = userService.createUser(user);
    //     return ResponseEntity.ok(newUser);
    // }

    @PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    // Check if username or email is already taken
    if (userService.usernameExists(user.getUsername())) {
        return ResponseEntity.badRequest().body("Username is already taken.");
    }
    if (userService.emailExists(user.getEmail())) {
        return ResponseEntity.badRequest().body("Email is already taken.");
    }

    // Log for debugging
    System.out.println("Creating user: " + user.getUsername());

    // Create and return the new user
    User newUser = userService.createUser(user);
    System.out.println("User created successfully with ID: " + newUser.getUserId());
    
    return ResponseEntity.ok("Successfully Created");
}


@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody User user) {
    Optional<User> existingUser = userService.findByUsername(user.getUsername());
    if (existingUser.isPresent()) {
        if (existingUser.get().getPassword().equals(user.getPassword())) {
            // Mock token for testing purposes
            String token = "mock-jwt-token";

            User loggedInUser = existingUser.get();
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "token", token,
                "message", "Login successful!",
                "user", Map.of(
                    "userId", loggedInUser.getUserId(),
                    "username", loggedInUser.getUsername(),
                    "role", loggedInUser.getRole()
                )
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Invalid password."
            ));
        }
    }
    return ResponseEntity.status(401).body(Map.of(
        "success", false,
        "message", "User not found."
    ));
}



    @GetMapping()
    public ResponseEntity <List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        Optional <User> user = userService.findByUsername(username);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
}
