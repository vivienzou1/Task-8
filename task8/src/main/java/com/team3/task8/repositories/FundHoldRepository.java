package com.team3.task8.repositories;

import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundHoldRepository extends JpaRepository<FundHold, Long> {
    FundHold findByName(String name);
}
