package com.sadvit.repositories;

import com.sadvit.models.Network;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sadvit on 4/16/16.
 */
public interface NetworkRepository extends JpaRepository<Network, Long> {

    List<Network> findByUserId(Long userId);

}
