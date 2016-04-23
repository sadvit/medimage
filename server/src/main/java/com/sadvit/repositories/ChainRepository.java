package com.sadvit.repositories;

import com.sadvit.models.Chain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by sadvit on 4/16/16.
 */
public interface ChainRepository extends JpaRepository<Chain, Long> {

    Set<Chain> findByUserId(Long userId);

}