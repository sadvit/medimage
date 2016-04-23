package com.sadvit.to;

import java.util.List;

/**
 * Created by sadvit on 4/19/16.
 */
public class NetworkTO {

    private Long id;

    private String name;

    private List<ResultTO> results;

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

    public List<ResultTO> getResults() {
        return results;
    }

    public void setResults(List<ResultTO> results) {
        this.results = results;
    }

}
