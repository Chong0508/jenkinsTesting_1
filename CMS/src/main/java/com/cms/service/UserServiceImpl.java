package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.User;
import com.cms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // LIST ALL
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET BY ID
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", id));
    }

    // CREATE
    @Override
    public User createUser(User user) {
        // you can add extra logic here later (e.g. hash password)
        return userRepository.save(user);
    }

    // UPDATE
    @Override
    public User updateUser(Long id, User userDetails) {
        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", id));

        // ⚠️ adjust setters to match your actual entity field names
        existing.setName(userDetails.getName());
        existing.setEmail(userDetails.getEmail());
        existing.setAffiliation(userDetails.getAffiliation());
        existing.setPasswordHash(userDetails.getPasswordHash());

        return userRepository.save(existing);
    }

    // DELETE
    @Override
    public void deleteUser(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", id));
        userRepository.delete(existing);
    }
}
