package com.sadvit.models;

import com.sadvit.operations.chains.ChainElement;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * Created by sadvit on 28.11.15.
 */
public class Chain extends Entity {

    private String name;

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

}
