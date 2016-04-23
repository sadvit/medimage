package com.sadvit.converters;

import com.sadvit.models.Value;
import com.sadvit.to.ValueTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/23/16.
 */
public class ValueConverter implements Converter<ValueTO, Value> {

    @Override
    public Value convert(ValueTO valueTO) {
        Value value = new Value();
        BeanUtils.copyProperties(valueTO, value);
        return value;
    }

}
