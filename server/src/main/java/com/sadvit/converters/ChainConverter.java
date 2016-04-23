package com.sadvit.converters;

import com.sadvit.models.Chain;
import com.sadvit.to.ChainTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/23/16.
 */
public class ChainConverter implements Converter<ChainTO, Chain> {

    @Override
    public Chain convert(ChainTO chainTO) {
        Chain chain = new Chain();
        BeanUtils.copyProperties(chainTO, chain);
        chain.setChainElements(chainTO.getChainElements());
        return chain;
    }

}
