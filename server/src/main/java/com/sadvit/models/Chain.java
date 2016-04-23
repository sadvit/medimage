package com.sadvit.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sadvit on 28.11.15.
 */
@Entity
@Table(name = "CHAINS")
public class Chain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chain_id", nullable = false)
    private Long id;

    private String name;

    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

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

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
