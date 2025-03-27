package com.huongag.SpringCRUDAPI.repo;

import com.huongag.SpringCRUDAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
//    @Query("SELECT p from Users p WHERE "+
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(p.age) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(p.address) LIKE LOWER(CONCAT('%', :keyword, '%'))"
//    )
//    List<Users> searchUsersByKeyWord(String keyword);
}
