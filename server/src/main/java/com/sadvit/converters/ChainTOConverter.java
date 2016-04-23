package com.sadvit.converters;

import com.sadvit.models.Chain;
import com.sadvit.to.ChainTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/23/16.
 */
public class ChainTOConverter implements Converter<Chain, ChainTO> {

    @Override
    public ChainTO convert(Chain chain) {
        ChainTO chainTO = new ChainTO();
        BeanUtils.copyProperties(chain, chainTO);
        chainTO.setChainElements(chain.getChainElements());
        return chainTO;
    }

}