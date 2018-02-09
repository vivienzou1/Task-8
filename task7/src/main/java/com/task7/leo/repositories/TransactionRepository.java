package com.task7.leo.repositories;

import com.task7.leo.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedQuery;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("select t from Transaction t order by t.date")
    List<Transaction> findAll();

    Transaction findById(Long id);
    List<Transaction> findByIdAfter(Long id);
    List<Transaction> findByCustomer_Username(String username);

    @Modifying
    @Query("update Transaction as t set t.date = ?1 where t.id = ?2")
    int updateDateById(Date date, long id);

    @Modifying
    @Query("update Transaction as t set t.share = ?1 where t.id = ?2")
    int updateShareById(double share, long id);

    @Modifying
    @Query("update Transaction as t set t.amount = ?1 where t.id = ?2")
    int updateAmountById(double amount, long id);

    @Modifying
    @Query("update Transaction as t set t.price = ?1 where t.id = ?2")
    int updatePriceById(double price, long id);
}
