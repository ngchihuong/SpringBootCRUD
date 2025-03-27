package com.huongag.SpringCRUDAPI.service.impl;

import com.huongag.SpringCRUDAPI.model.Users;
import com.huongag.SpringCRUDAPI.repo.UserRepository;
import com.huongag.SpringCRUDAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;

    public Users addUser(Users user) {
        return repo.save(user);
    }

    public Users updateUser(int id, Users user) {
        return repo.save(user);
    }

    public Users getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        repo.deleteById(id);
    }

//    @Override
//    public List<Users> searchUserByKeyWord(String keyword) {
//        return repo.searchUsersByKeyWord(keyword);
//    }

    public List<Users> getUsers () {
        return repo.findAll();
    }
}
