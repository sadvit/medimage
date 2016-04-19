package com.sadvit.dto;

import com.sadvit.models.RecognizeResult;
import com.sadvit.models.RecognizeValue;

/**
 * Created by sadvit on 4/19/16.
 */
public class RecognizeValueInfo implements DTO<RecognizeValue> {

    private String imageId;

    private String tempId;

    private String value;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    @Override
    public RecognizeValue convertToEntity() {
        RecognizeValue recognizeValue = new RecognizeValue();
        recognizeValue.setValue(value);
        recognizeValue.setImageId(imageId);
        return recognizeValue;
    }

    @Override
    public void createFromEntity(RecognizeValue entity) {
        value = entity.getValue();
        imageId = entity.getImageId();
    }

}
