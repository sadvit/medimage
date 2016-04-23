package com.sadvit.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sadvit on 4/17/16.
 */
@Entity
@Table(name = "VALUES")
public class Value implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "value_id", nullable = false)
    private Long id;

    private String imageId;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
