package com.task7.leo.repositories;

import com.task7.leo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByUsername(String username);

    @Modifying
    @Query("update Employee as e set e.password = ?1 where e.id = ?2")
    void updatePasswordById(String password, long id);
}
