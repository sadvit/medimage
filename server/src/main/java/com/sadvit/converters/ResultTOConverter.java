package com.sadvit.converters;

import com.sadvit.models.Result;
import com.sadvit.models.Value;
import com.sadvit.to.ResultTO;
import com.sadvit.to.ValueTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sadvit on 4/23/16.
 */
public class ResultTOConverter implements Converter<Result, ResultTO> {

    private ConversionService conversionService;

    @Override
    public ResultTO convert(Result result) {
        ResultTO resultTO = new ResultTO();
        BeanUtils.copyProperties(result, resultTO);
        List<ValueTO> valuesTO = result.getValues().stream()
                .map(value -> conversionService.convert(value, ValueTO.class))
                .collect(Collectors.toList());
        resultTO.setValues(valuesTO);
        return resultTO;
    }

    public ResultTOConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

}
