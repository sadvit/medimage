package com.sadvit.converters;

import com.sadvit.models.Result;
import com.sadvit.models.Value;
import com.sadvit.to.ResultTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sadvit on 4/23/16.
 */
public class ResultConverter implements Converter<ResultTO, Result> {

    private ConversionService conversionService;

    @Override
    public Result convert(ResultTO resultTO) {
        Result result = new Result();
        BeanUtils.copyProperties(resultTO, result);
        Set<Value> values = resultTO.getValues().stream()
                .map(valueTO -> conversionService.convert(valueTO, Value.class))
                .collect(Collectors.toSet());
        result.setValues(values);
        return result;
    }

    public ResultConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

}
