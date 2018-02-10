package com.team3.task8.repositories;

import com.team3.task8.domain.Fund;
import com.team3.task8.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FundRepository extends JpaRepository<Fund, Long> {

    Fund findBySymbol(String symbol);

    @Modifying
    @Query("update Fund as f set f.price = ?1 where f.symbol = ?2")
    int updatePriceBySymbol(String price, String symbol);
}
