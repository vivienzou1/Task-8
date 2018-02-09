package com.task7.leo.repositories;

import com.task7.leo.domain.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FundRepository extends JpaRepository<Fund, Long> {

    Fund findByFundName(String fundName);
    Fund findByFundSymbol(String fundSymbol);
    Fund findById(long id);

    @Modifying
    @Query("update Fund as f set f.price = ?1 where f.fundSymbol = ?2")
    void updatePriceBySymbol(double price, String fundSymbol);

    @Modifying
    @Query("update Fund as f set f.lastTransition = ?1 where f.fundSymbol = ?2")
    void updateLastTransitionById(Date date, String fundSymbol);

}
