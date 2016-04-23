package com.sadvit.repositories;

import com.sadvit.models.Chain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
public interface ChainRepository extends JpaRepository<Chain, Long>, ChainRepositoryCustom {

    @Query("SELECT u.chains "
            + " FROM User u "
            + " WHERE u.id = :id")
    Set<Chain> findByUserId(@Param("id") Long id);

}