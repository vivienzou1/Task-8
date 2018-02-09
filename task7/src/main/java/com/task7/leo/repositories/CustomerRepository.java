package com.task7.leo.repositories;

import com.task7.leo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUsername(String username);
    Customer findById(long id);

    @Modifying
    @Query("update Customer as c set c.balance = ?1 where c.id = ?2")
    int updateBalanceById(double balance, long id);

    @Modifying
    @Query("update Customer as c set c.password = ?1 where c.id = ?2")
    void updatePasswordById(String password, long id);

    @Modifying
    @Query("update Customer as c set c.password = ?1 where c.username = ?2")
    void updatePasswordByUsername(String password, String username);
}
