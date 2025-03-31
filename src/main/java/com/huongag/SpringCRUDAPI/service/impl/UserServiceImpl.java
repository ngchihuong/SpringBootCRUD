//package com.huongag.SpringCRUDAPI.service.impl;
//
//import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
//import com.huongag.SpringCRUDAPI.model.Users;
//import com.huongag.SpringCRUDAPI.repo.UserRepository;
//import com.huongag.SpringCRUDAPI.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository repo;
//
//    public UserServiceImpl(UserRepository repo) {
//        this.repo = repo;
//    }
//
//    public ApiResponseDto<Users> addUser(Users user) {
////        Users user1 = new Users();
////        user1.setEmail(user1.getEmail());
////        Example<Users> match = Example.of(user1);
////        Optional<Users> result = repo.findOne(match);
////        if (!result.isEmpty()) {
////            System.out.println(user);
////         return user;
////        }else {
////            return repo.save(user);
////        }
//        Users user1 = repo.getUserByEmail(user.getEmail());
//        if (user1 != null) {
//            ApiResponseDto<Users> res = new ApiResponseDto<>(
//                    HttpStatus.FOUND.value(),
//                    "Email is exiting!",
//                    null
//            );
//            return res;
//        }else {
//            ApiResponseDto<Users> res = new ApiResponseDto<>(
//                    HttpStatus.CREATED.value(),
//                    "Success!",
//                    repo.save(user)
//            );
//            return res;
//        }
//    }
//
//    public Users updateUser(int id, Users user) {
//        return repo.save(user);
//    }
//
//    public Users getUserById(int id) {
//        return repo.findById(id).orElse(null);
//    }
//
//    public void deleteUser(int id) {
//        repo.deleteById(id);
//    }
//
//    public List<Users> searchUserByKeyWord(String keyword) {
//        return repo.searchUsersByKeyWord(keyword);
//    }
//
//    @Override
//    public ApiResponseDto<Page<Users>> getUsersWithPaginate(int page, int size, String sortBy, boolean ascending) {
//        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        ApiResponseDto<Page<Users>> res = new ApiResponseDto<>(
//                HttpStatus.CREATED.value(),
//                "Success!",
//                repo.findAll(pageable)
//        );
//        return res;
////        return repo.findAll(pageable);
//    }
//
//    public List<Users> getUsers() {
//        return repo.findAll();
//    }
//}
