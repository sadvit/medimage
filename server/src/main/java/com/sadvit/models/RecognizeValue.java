package com.sadvit.models;

import javax.persistence.*;

/**
 * Created by sadvit on 4/17/16.
 */
@Entity
@Table(name = "RECOGNIZE_VALUES")
public class RecognizeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String imageId;

    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public RecognizeValue(String imageId, String value) {
        this.imageId = imageId;
        this.value = value;
    }

    public RecognizeValue() {
    }

}
