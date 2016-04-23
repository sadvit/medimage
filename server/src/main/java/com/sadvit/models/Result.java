package com.sadvit.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by sadvit on 4/17/16.
 */
@Entity
@Table(name = "RESULTS")
public class Result implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Value> values;

    @JoinColumn(name = "networkId", referencedColumnName = "network_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Network network;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

}