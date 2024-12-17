package com.revature.ticket_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ticket_backend.Repository.UserRepository;
import com.revature.ticket_backend.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String userName){
        return userRepository.findByUsername(userName);
    }
    public boolean usernameExists(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean emailExists(String email){
        return userRepository.existsByEmail(email);

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



    
}
