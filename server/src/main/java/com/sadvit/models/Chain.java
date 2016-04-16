package com.sadvit.models;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by sadvit on 28.11.15.
 */
@Entity
@Table(name = "CHAINS")
public class Chain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ChainElement> chainElements;

    public List<ChainElement> getChainElements() {
        return chainElements;
    }

    public void setChainElements(List<ChainElement> chainElements) {
        this.chainElements = chainElements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
