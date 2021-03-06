package com.health.tracker;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


// This is auto implemented by spring into a bean called userRepository
    // spring makes it the same name but changes the case from User to user
// CRUD, Create Read Update Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    
    // public List<User> getUsersById(Long id); 
    @Query("SELECT u from User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u from User u WHERE u.id = ?1")
    public User findById(UUID id);
}
