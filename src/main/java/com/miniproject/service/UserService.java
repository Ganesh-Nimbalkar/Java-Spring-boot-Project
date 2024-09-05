package com.miniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.dao.UserRepository;
import com.miniproject.model.User;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    
  
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public User updateUser(Long id, User updatedUser) {
        // Retrieve the existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update the user fields with the new information
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setPhonenumber(updatedUser.getPhonenumber());
        existingUser.setAddress(updatedUser.getAddress());
        
        // Update other fields as needed

        // Save and return the updated user
        return userRepository.save(existingUser);
    }
    public User saveUser(User user) {
    	 User existingUser = userRepository.findByEmail(user.getEmail());
    	    if (existingUser != null) {
    	        // If the email already exists, you may throw an exception, return null, or handle it in another way
    	        throw new RuntimeException("Email address already exists");
    	    } else {
    	        // If the email doesn't exist, save the user
    	        return userRepository.save(user);
    	    }
        
        
    }



}
