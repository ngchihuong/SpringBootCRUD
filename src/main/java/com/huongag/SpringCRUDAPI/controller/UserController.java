//package com.huongag.SpringCRUDAPI.controller;
//
//import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
//import com.huongag.SpringCRUDAPI.dto.UserDto;
//import com.huongag.SpringCRUDAPI.model.Users;
//import com.huongag.SpringCRUDAPI.service.UserService;
//import com.huongag.SpringCRUDAPI.service.impl.UserServiceImpl;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@RestController
////@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//
//    public UserController(UserService userService) {
//        super();
//        this.userService = userService;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponseDto<UserDto>> addUser(@RequestBody UserDto user) {
////        try {
////            Users user1 = userService.addUser(user);
////
////            ApiResponseDto<Users> res = new ApiResponseDto<>(
////                    HttpStatus.CREATED.value(),
////                    "Success!",
////                    user1
////            );
////            return ResponseEntity.status(HttpStatus.CREATED).body(res);
////        } catch (Exception e) {
////            ApiResponseDto<Users> errorResponse = new ApiResponseDto<>(
////                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
////                    e.getMessage(),
////                    null
////            );
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
////        }
//        try {
//            Users newUser = new Users();
//            newUser.setName(user.getName());
//            newUser.setEmail(user.getEmail());
//
//            newUser.setPassword(encoder.encode(user.getPassword()));
//
//            newUser.setAge(user.getAge());
//            newUser.setAddress(user.getAddress());
//
//            ApiResponseDto<Users> userEntity = userService.addUser(newUser);
//
//            UserDto userDto = new UserDto();
//            userDto.setName(userEntity.getData().getName());
//            userDto.setEmail(userEntity.getData().getEmail());
//            userDto.setAge(userEntity.getData().getAge());
//            userDto.setPassword("hidden");
//            userDto.setAddress(userEntity.getData().getAddress());
//
//
//            ApiResponseDto<UserDto> res = new ApiResponseDto<UserDto>(
//                    HttpStatus.CREATED.value(),
//                    "Success!",
//                    userDto
//            );
//            return ResponseEntity.status(HttpStatus.CREATED).body(res);
////            return null;
//        } catch (Exception e) {
//            ApiResponseDto<UserDto> errorResponse = new ApiResponseDto<>(
//                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                    e.getMessage(),
//                    null
//            );
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<List<Users>> user() {
//        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
//    }
//
//    @GetMapping("/users-paginate")
//    public ResponseEntity<Page<UserDto>> getUsersWithPaginate (
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(defaultValue = "true") boolean ascending
//    ) {
//        try {
//            ApiResponseDto<Page<Users>> userEntity = userService.getUsersWithPaginate(page, size, sortBy, ascending);
//
//            UserDto userDto = new UserDto();
////            userDto.setName(userEntity.getData().getName());
////            userDto.setEmail(userEntity.getData().getEmail());
////            userDto.setAge(userEntity.getData().getAge());
////            userDto.setPassword("hidden");
////            userDto.setAddress(userEntity.getData().getAddress());
//            ApiResponseDto<UserDto> res = new ApiResponseDto<UserDto>(
//                    HttpStatus.OK.value(),
//                    "Success!",
//                    userDto
//            );
//            return ResponseEntity.status(HttpStatus.CREATED).body((Page<UserDto>)res);
//        } catch (Exception e) {
//            ApiResponseDto<UserDto> errorResponse = new ApiResponseDto<>(
//                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                    e.getMessage(),
//                    null
//            );
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((Page<UserDto>) errorResponse);
//        }
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable int id) {
//        Users user = userService.getUserById(id);
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Users user) {
//        Users user1 = null;
//        try {
//            user1 = userService.updateUser(id, user);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
//        }
//        if (user1 != null) {
//            return new ResponseEntity<>("Updated!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to update!", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable int id) {
//        Users user = userService.getUserById(id);
//        if (user != null) {
//            userService.deleteUser(id);
//            return new ResponseEntity<>("Deleted", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to delete!", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/user/search")
//    public ResponseEntity<List<Users>> searchUserByKeyWord(String keyword) {
//        System.out.println("Key word" + keyword);
//        List<Users> users = userService.searchUserByKeyWord(keyword);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//}
