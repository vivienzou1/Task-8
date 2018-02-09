package com.task7.leo.repositories;

import com.task7.leo.domain.Fund;
import com.task7.leo.domain.LastTransitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LastTransitionIdRepository extends JpaRepository<LastTransitionId, Long> {

    LastTransitionId findById(long id);

    @Modifying
    @Query("update LastTransitionId as l set l.lastTransitionId = ?1 where l.id = ?2")
    void updateLastIdById(long lastId, long id);

}
