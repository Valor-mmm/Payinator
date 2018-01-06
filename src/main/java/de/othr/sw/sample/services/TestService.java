package de.othr.sw.sample.services;

import de.othr.sw.sample.entity.TestEntity;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@WebService
@SessionScoped
public class TestService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private int sessionCounter;

    @Resource
    private WebServiceContext context;

    @Inject
    private AuthTestService authTestService;

    @WebMethod
    @Transactional
    public List<TestEntity> listEntitites() {

        TypedQuery<TestEntity> allEntities = em.createQuery("SELECT te FROM TestEntity te", TestEntity.class);

        List<TestEntity> result = allEntities.getResultList();

        return result;
    }

    @WebMethod
    @Transactional
    public TestEntity createEntity(TestEntity entity) {
        sessionCounter++;

        MessageContext messageContext = context.getMessageContext();
        HttpSession session = ((HttpServletRequest) messageContext.get(MessageContext.SERVLET_REQUEST)).getSession();
        em.persist(entity);

        return entity;
    }

    @WebMethod
    public int getSessionCounter() {
        return sessionCounter;
    }

    @WebMethod
    public String getAuthString() {
        return authTestService.getAuth();
    }
}
