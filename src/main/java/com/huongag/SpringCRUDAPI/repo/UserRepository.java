//package com.huongag.SpringCRUDAPI.repo;
//
//import com.huongag.SpringCRUDAPI.dto.ApiResponsePaginateDto;
//import com.huongag.SpringCRUDAPI.model.Users;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserRepository extends JpaRepository<Users, Integer> {
//    @Query("SELECT p from users p WHERE "+
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(p.address) LIKE LOWER(CONCAT('%', :keyword, '%'))"
//    )
//    List<Users> searchUsersByKeyWord(String keyword);
//
//    @Query("SELECT p from users p WHERE LOWER(p.email) = LOWER(CONCAT('%', :email, '%'))")
//    Users getUserByEmail(String email);
//
//    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% ORDER BY u.id limit :limit offset :offset")
//    ApiResponsePaginateDto<List<Users>> getUsersWithPaginate(int page, int size, String sortBy, boolean ascending);
//
//}
