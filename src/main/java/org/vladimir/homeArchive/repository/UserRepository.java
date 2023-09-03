package org.vladimir.homeArchive.repository;

import org.vladimir.homeArchive.model.entity.User;

import java.util.List;

public interface UserRepository {

    public User getById(Long id);

    public List<User> getAllUsers();

    public Long createUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);
}
