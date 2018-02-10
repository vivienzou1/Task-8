package com.team3.task8.repositories;


import com.team3.task8.domain.FundHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundHoldRepository extends JpaRepository<FundHold, Long> {

    FundHold findByUsernameAndName(String username, String name);

    List<FundHold> findByUsername(String username);

    @Modifying
    @Query("update FundHold as f set f.shares = ?1 where f.id = ?2")
    int updateSharesById(String shares, long id);
}
