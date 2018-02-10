package com.team3.task8.repositories;

import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Query("update User as u set u.cash = ?1 where u.username = ?2")
    int updateCashByUsername(double cash, String username);
}
