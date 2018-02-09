package com.task7.leo.repositories;

import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Fund;
import com.task7.leo.domain.FundHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FundHoldRepository extends JpaRepository<FundHold, Long> {
    FundHold findByCustomerAndFund(Customer customer, Fund fund);

    @Modifying
    @Query("update FundHold as f set f.share = ?1 where f.id = ?2")
    int updateShareById(double share, long id);

    List<FundHold> findFundHoldsByCustomer_Username(String username);
}
