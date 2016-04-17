package com.sadvit.models;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by sadvit on 4/17/16.
 */
@Entity
@Table(name = "RECOGNIZE_RESULTS")
public class RecognizeResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RecognizeValue> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<RecognizeValue> getValues() {
        return values;
    }

    public void setValues(Set<RecognizeValue> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
