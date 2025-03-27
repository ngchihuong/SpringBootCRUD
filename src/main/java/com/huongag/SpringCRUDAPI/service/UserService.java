package com.huongag.SpringCRUDAPI.service;


import com.huongag.SpringCRUDAPI.model.Users;

import java.util.List;

public interface UserService {
    Users addUser(Users user);

    Users updateUser(int id, Users user);

    Users getUserById(int id);

    void deleteUser(int id);

//    List<Users> searchUserByKeyWord(String keyword);
}
