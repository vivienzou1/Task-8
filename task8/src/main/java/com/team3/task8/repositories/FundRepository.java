package com.team3.task8.repositories;

import com.team3.task8.domain.Fund;
import com.team3.task8.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Long> {
    Fund findBySymbol(String symbol);
}
