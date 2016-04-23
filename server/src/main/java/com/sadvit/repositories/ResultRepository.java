package com.sadvit.repositories;

import com.sadvit.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByNetworkId(Long networkId);

}
