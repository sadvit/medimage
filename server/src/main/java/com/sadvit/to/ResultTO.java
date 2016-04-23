package com.sadvit.to;

import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
public class ResultTO {

    private Long id;

    private String name;

    private List<ValueTO> values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ValueTO> getValues() {
        return values;
    }

    public void setValues(List<ValueTO> values) {
        this.values = values;
    }

}
