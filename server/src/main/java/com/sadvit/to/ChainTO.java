package com.sadvit.to;

import com.sadvit.models.ChainElement;

import java.util.List;

/**
 * Created by sadvit on 4/23/16.
 */
public class ChainTO {

    private Long id;

    private String name;

    private List<ChainElement> chainElements;

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

    public List<ChainElement> getChainElements() {
        return chainElements;
    }

    public void setChainElements(List<ChainElement> chainElements) {
        this.chainElements = chainElements;
    }

}
