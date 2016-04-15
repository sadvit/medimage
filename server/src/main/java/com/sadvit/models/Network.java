package com.sadvit.models;

import org.neuroph.nnet.MultiLayerPerceptron;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 3/20/16.
 */
public class Network {

    private Integer id;

    private String name;

    private MultiLayerPerceptron perceptron;

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
