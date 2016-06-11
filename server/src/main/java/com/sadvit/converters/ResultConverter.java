package com.sadvit.converters;

import com.sadvit.models.Result;
import com.sadvit.models.Value;
import com.sadvit.to.ResultTO;
import com.sadvit.to.ValueTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/23/16.
 */
public class ResultConverter implements Converter<ResultTO, Result> {

    private ConversionService conversionService;

    @Override
    public Result convert(ResultTO resultTO) {
        Result result = new Result();
        BeanUtils.copyProperties(resultTO, result);
        List<ValueTO> resultTOValues = resultTO.getValues();
        Set<Value> values = new HashSet<>();
        for (ValueTO valueTO : resultTOValues) {
            Value convert = conversionService.convert(valueTO, Value.class);
            values.add(convert);
        }
        result.setValues(values);
        return result;
    }

    public ResultConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

}
