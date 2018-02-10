package com.team3.task8.repositories;

import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    FundHold findByUsernameAndFundHoldsEquals(String username, FundHold fundHold);

}
