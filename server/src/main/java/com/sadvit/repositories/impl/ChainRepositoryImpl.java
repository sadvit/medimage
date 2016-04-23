package com.sadvit.repositories.impl;

import com.sadvit.models.Chain;
import com.sadvit.models.User;
import com.sadvit.repositories.ChainRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

/**
 * Created by sadvit on 4/23/16.
 */
@Repository
@Transactional
public class ChainRepositoryImpl implements ChainRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addChain(Long userId, Chain chain) {
        User user = entityManager.find(User.class, userId);
        Set<Chain> chains = user.getChains();
        chains.add(chain);
        entityManager.merge(user);
    }

}
