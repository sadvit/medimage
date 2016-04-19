package com.sadvit.dto;

/**
 * Created by sadvit on 4/19/16.
 */
public interface DTO<T> {

    T convertToEntity();

    void createFromEntity(T entity);

}
