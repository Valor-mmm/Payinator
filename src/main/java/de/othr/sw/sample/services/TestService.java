package de.othr.sw.sample.services;

import de.othr.sw.sample.entity.TestEntity;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@SessionScoped
public class TestService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<TestEntity> listEntitites() {

        TypedQuery<TestEntity> allEntities = em.createQuery("SELECT te FROM TestEntity te", TestEntity.class);

        List<TestEntity> result = allEntities.getResultList();

        return result;
    }

    @Transactional
    public TestEntity createEntity(TestEntity entity) {

        em.persist(entity);

        return entity;
    }
}
