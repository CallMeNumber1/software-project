package com.example.softwareproject.repository.impl;

import javax.persistence.EntityManager;

import com.example.softwareproject.repository.CustomizedRepository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomizedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomizedRepository<T, ID> {

    private EntityManager entityManager;

    public CustomizedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        // TODO Auto-generated constructor stub
        this.entityManager = entityManager;
    }
    
    @Override
    public T refresh(T t) {
        entityManager.refresh(t);
        return t;
    }

}