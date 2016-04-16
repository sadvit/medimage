package com.sadvit.models;

import org.hibernate.annotations.Type;
import org.neuroph.nnet.MultiLayerPerceptron;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 3/20/16.
 */
@Entity
@Table(name = "NETWORKS")
public class Network {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Type(type = "serializable")
    private MultiLayerPerceptron perceptron;

    @ElementCollection
    private List<String> answers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiLayerPerceptron getPerceptron() {
        return perceptron;
    }

    public void setPerceptron(MultiLayerPerceptron perceptron) {
        this.perceptron = perceptron;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
