package com.sadvit.dto;

import com.sadvit.models.NetworkEntity;
import com.sadvit.models.RecognizeResult;
import com.sadvit.models.RecognizeValue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sadvit on 4/19/16.
 */
public class NetworkEntityInfo implements DTO<NetworkEntity> {

    private Integer id;

    private String name;

    private List<RecognizeResultInfo> recognizeResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecognizeResultInfo> getRecognizeResults() {
        return recognizeResults;
    }

    public void setRecognizeResults(List<RecognizeResultInfo> recognizeResults) {
        this.recognizeResults = recognizeResults;
    }

    @Override
    public NetworkEntity convertToEntity() {
        NetworkEntity result = new NetworkEntity();
        Set<RecognizeResult> recognizeResults = new HashSet<>();
        this.recognizeResults.forEach(value -> {
            recognizeResults.add(value.convertToEntity());
        });
        result.setRecognizeResults(recognizeResults);
        result.setName(name);
        result.setId(id);
        return result;
    }

    @Override
    public void createFromEntity(NetworkEntity entity) {
        recognizeResults = new ArrayList<>();
        entity.getRecognizeResults().forEach(value -> {
            RecognizeResultInfo recognizeResultInfo = new RecognizeResultInfo();
            recognizeResultInfo.createFromEntity(value);
            recognizeResults.add(recognizeResultInfo);
        });
        name = entity.getName();
        id = entity.getId();
    }

}
