package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с id '" + id + "' не найден!")
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        findById(user.getId());
        userRepository.update(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
