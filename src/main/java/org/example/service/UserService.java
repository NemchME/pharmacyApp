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

    public List<User> findAll(int page, int size) {
        return userRepository.findAll(page, size);
    }

    public int getTotalPages(int size) {
        int total = userRepository.countAll();
        return (int) Math.ceil((double) total / size);
    }

    public List<User> filter(String search) {
        return userRepository.filter(search);
    }

    public List<User> sort(String sort, String comparator) {

        return userRepository.sort(sort, comparator);
    }

    public void update(User user) {
        if (findById(user.getId()) != null) {
            userRepository.update(user);
        }
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
