package com.sadvit.repositories;

import com.sadvit.models.Chain;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sadvit on 4/23/16.
 */
@Transactional
public interface ChainRepositoryCustom {

    void addChain(Long userId, Chain chain);

}
