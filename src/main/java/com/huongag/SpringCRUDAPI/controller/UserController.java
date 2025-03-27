package com.huongag.SpringCRUDAPI.controller;

import com.huongag.SpringCRUDAPI.model.Users;
import com.huongag.SpringCRUDAPI.service.impl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        try {
            Users user1 = userService.addUser(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Users>> user() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Users user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Users user) {
        Users user1 = null;
        try {
            user1 = userService.updateUser(id, user);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (user1 != null) {
            return new ResponseEntity<>("Updated!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        Users user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete!", HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/user/{keyword}")
//    public ResponseEntity<List<Users>> searchUserByKeyWord(String keyword) {
//        System.out.println("Key word");
//        List<Users> users = userService.searchUserByKeyWord(keyword);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
}
