package com.huongag.SpringCRUDAPI.repo;

import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    boolean existsByEmail(String email);
    Optional<AccountEntity> findByEmail(String email);
    @Query(value ="SELECT * FROM accounts a ORDER BY a.id LIMIT :limit OFFSET :offset",nativeQuery = true)
    List<AccountEntity> getAccountsWithPaginate(int limit, int offset);

    @Query(value = "SELECT COUNT(id) FROM accounts",nativeQuery = true)
    Long getTotalCount();

    @Query(value = "SELECT * from accounts p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.email) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY p.id LIMIT :limit OFFSET :offset",
    nativeQuery = true)
    List<AccountEntity> searchProducts(int limit, int offset,String keyword);

}
