//package com.huongag.SpringCRUDAPI.service;
//
//
//import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
//import com.huongag.SpringCRUDAPI.model.Users;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//public interface UserService {
//    ApiResponseDto<Users> addUser(Users user);
//
//    Users updateUser(int id, Users user);
//
//    Users getUserById(int id);
//
//    void deleteUser(int id);
//    List<Users> getUsers();
//    List<Users> searchUserByKeyWord(String keyword);
//
//    ApiResponseDto<Page<Users>> getUsersWithPaginate(int page, int size, String sortBy, boolean ascending);
//}
