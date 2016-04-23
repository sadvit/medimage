package com.sadvit.converters;

import com.sadvit.models.Value;
import com.sadvit.to.ValueTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/23/16.
 */
public class ValueTOConverter implements Converter<Value, ValueTO> {

    @Override
    public ValueTO convert(Value value) {
        ValueTO valueTO = new ValueTO();
        BeanUtils.copyProperties(value, valueTO);
        return valueTO;
    }

}
