package com.example.banking.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email = ?1")
    public Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    public Optional<User> findUserByCredentials(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public Optional<User> findUserByUsername(String username);

}
