package com.sadvit.operations.blur;

import javax.persistence.*;

/**
 * Created by vitaly.sadovskiy on 24.12.2015.
 */
@Entity
@Table(name = "BLUR_PARAMS")
public class BlurParams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int radius;

    @Enumerated
    private BlurType type;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public BlurType getType() {
        return type;
    }

    public void setType(BlurType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
