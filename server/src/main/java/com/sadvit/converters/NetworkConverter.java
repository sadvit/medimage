package com.sadvit.converters;

import com.sadvit.models.Network;
import com.sadvit.to.NetworkTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/23/16.
 */
public class NetworkConverter implements Converter<NetworkTO, Network> {

    @Override
    public Network convert(NetworkTO networkTO) {
        Network network = new Network();
        BeanUtils.copyProperties(networkTO, network);
        return network;
    }

}