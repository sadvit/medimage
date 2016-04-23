package com.sadvit.converters;

import com.sadvit.models.Network;
import com.sadvit.to.NetworkTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/23/16.
 */
public class NetworkTOConverter implements Converter<Network, NetworkTO> {

    @Override
    public NetworkTO convert(Network network) {
        NetworkTO networkTO = new NetworkTO();
        BeanUtils.copyProperties(network, networkTO);
        return networkTO;
    }

}