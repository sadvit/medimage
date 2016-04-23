package com.sadvit.configs;

import com.sadvit.converters.*;
import com.sadvit.models.User;
import com.sadvit.to.UserTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * Created by sadvit on 4/22/16.
 */
@Configuration
public class ConversionConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new UserConverter());
        conversionService.addConverter(new UserTOConverter());
        conversionService.addConverter(new ValueConverter());
        conversionService.addConverter(new ValueTOConverter());
        conversionService.addConverter(new ResultConverter(conversionService));
        conversionService.addConverter(new ResultTOConverter(conversionService));
        conversionService.addConverter(new ChainConverter());
        conversionService.addConverter(new ChainTOConverter());
        conversionService.addConverter(new NetworkConverter());
        conversionService.addConverter(new NetworkTOConverter());
        return conversionService;
    }

}
