package com.sadvit.dto;

import com.sadvit.models.RecognizeResult;
import com.sadvit.models.RecognizeValue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadvit on 4/19/16.
 */
public class RecognizeResultInfo implements DTO<RecognizeResult> {

    private Integer id;

    private String name;

    private Set<RecognizeValueInfo> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RecognizeValueInfo> getValues() {
        return values;
    }

    public void setValues(Set<RecognizeValueInfo> values) {
        this.values = values;
    }

    @Override
    public RecognizeResult convertToEntity() {
        RecognizeResult result = new RecognizeResult();
        Set<RecognizeValue> values = new HashSet<>();
        this.values.forEach(value -> {
            values.add(value.convertToEntity());
        });
        result.setValues(values);
        result.setName(name);
        result.setId(id);
        return result;
    }

    @Override
    public void createFromEntity(RecognizeResult entity) {
        values = new HashSet<>();
        entity.getValues().forEach(value -> {
            RecognizeValueInfo recognizeValue = new RecognizeValueInfo();
            recognizeValue.createFromEntity(value);
            values.add(recognizeValue);
        });
        name = entity.getName();
        id = entity.getId();
    }

}
