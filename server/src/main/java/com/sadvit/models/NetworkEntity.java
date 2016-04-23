package com.sadvit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.MultiLayerPerceptron;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sadvit on 3/20/16.
 */
@Entity
@Table(name = "NETWORKS")
public class NetworkEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @Type(type = "serializable")
    private NeuralNetwork neuralNetwork;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> answers;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, double[][]> memory;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RecognizeResult> recognizeResults;

    public Map<String, double[][]> getMemory() {
        return memory;
    }

    public void setMemory(Map<String, double[][]> memory) {
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void setPerceptron(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RecognizeResult> getRecognizeResults() {
        return recognizeResults;
    }

    public void setRecognizeResults(Set<RecognizeResult> recognizeResults) {
        this.recognizeResults = recognizeResults;
    }

}
