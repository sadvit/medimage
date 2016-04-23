package com.sadvit.models;

import org.hibernate.annotations.Type;
import org.neuroph.core.NeuralNetwork;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by sadvit on 3/20/16.
 */
@Entity
@Table(name = "NETWORKS")
public class Network implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "network_id", nullable = false)
    private Long id;

    private String name;

    @Type(type = "serializable")
    private NeuralNetwork neuralNetwork;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> answers;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, double[][]> memory;

    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
