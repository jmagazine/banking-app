package com.example.banking.user;

import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
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

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public User findUserByCredentials(String username, String password){
        Optional<User> userOptional = userRepository.findUserByCredentials(username, password);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }

    @Transactional
    public void addNewUser(@NotNull User user) {
        String email =  user.getEmail();
        String username = user.getUsername();
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()){
            throw new EmailAlreadyInUseException();
        }
        userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()){
            throw new UsernameAlreadyInUseException(String.format("Username %s is already taken.", username));
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(@NotNull Long userId){
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new UserNotFoundException(String.format("User with id %s does not exist", userId));
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String firstName, String lastName, String email, String username, String password) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User with id " + userId + " does not exist.");
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
                throw new EmailAlreadyInUseException(String.format("Email %s is already in use. Please sign in.", email));
            }
            user.setEmail(email);
        }
        if (username != null && !username.isEmpty() && !Objects.equals(user.getUsername(), username)){
            Optional<User> existingUser = userRepository.findUserByUsername(username);
        }
    }

}

