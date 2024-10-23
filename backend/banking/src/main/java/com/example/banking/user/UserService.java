package com.example.banking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("User already exists");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new IllegalStateException("Student with id " + userId+ " does not exist");
        }
        userRepository.deleteById(userId);
    }


    public void updateUser(Long userId, String firstName, String lastName, String email) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new IllegalStateException("User with id " + userId + " does not exist.");
        }
        User user = userOptional.get();
        if (firstName!= null && !firstName.isEmpty() &&
                !Objects.equals(user.getFirstName(), firstName)){
            user.setFirstName(firstName);
        }

        if (lastName != null && !lastName.isEmpty() &&
                !Objects.equals(user.getLastName(), lastName)){
            user.setLastName(lastName);
        }

        if (email != null && !email.isEmpty() &&
                !Objects.equals(user.getEmail(), email)){
            Optional<User> existingUser = userRepository.findUserByEmail(email);
            if (existingUser.isPresent()){
                throw new IllegalStateException("That email is already in use. Please sign in.");
            }
            user.setEmail(email);
        }
    }
}
