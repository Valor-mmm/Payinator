package de.othr.has44540.persistance.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

public abstract class SingleIdEntityRepository<IdType, Entity extends SingleIdEntity<IdType>> {

    public static final String PERSISTANCE_UNIT_NAME = "swPU";

    private static final Logger logger = Logger.getLogger(SingleIdEntityRepository.class.getName());

    private Class<Entity> entityClass;

    @PersistenceContext(unitName = SingleIdEntityRepository.PERSISTANCE_UNIT_NAME)
    private EntityManager em;

    public SingleIdEntityRepository(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public void persist(Entity entity) {
        em.persist(entity);
    }

    public Entity merge(Entity entity) {
        return em.merge(entity);
    }

    public void remove(Entity entity) {
        em.remove(entity);
    }

    public Entity findById(IdType id) {
        return em.find(entityClass, id);
    }

    public List<Entity> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Entity> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<Entity> criteriaAllQuery = criteriaQuery.select(rootEntry);
        TypedQuery<Entity> getAllQuery = em.createQuery(criteriaAllQuery);
        return getAllQuery.getResultList();
    }


}
