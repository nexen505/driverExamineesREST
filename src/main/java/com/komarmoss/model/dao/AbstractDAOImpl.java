package com.komarmoss.model.dao;

import com.komarmoss.model.entity.Identifiable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class AbstractDAOImpl<T extends Identifiable, ID extends Serializable>
        implements AbstractDAO<T, ID> {

    //    @PersistenceContext
//    protected EntityManager entityManager;
    @Autowired
    private SessionFactory factory;
    protected Class daoType;

    public AbstractDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
//        Configuration configuration = new Configuration().addAnnotatedClass(daoType);
//        configuration.configure("hibernate.cfg.xml");
//        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//        serviceRegistryBuilder.applySettings(configuration.getProperties());
//        factory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
    }

    protected Session getSession() {
        Session session = factory.getCurrentSession();
        return session;
    }

    @Override
    public T getItemById(final ID id) {
        return id != null ? (T) getSession().get(daoType, id) : null;
    }

    @Override
    public List<T> getAllItems() {
        return (List<T>) getSession().createQuery("from " + daoType.getSimpleName()).list();
    }

    @Override
    public void saveItem(T entity) {
        if (entity == null) return;
        getSession().save(entity);
    }

    @Override
    public void saveOrUpdateItem(T entity) {
        if (entity == null) return;
        if (entity.getId() == null) saveItem(entity);
        else updateItem(entity);
    }

    @Override
    public void updateItem(T entity) {
        if (entity == null) return;
        getSession().update(entity);
    }

    @Override
    public void removeItem(T entity) {
        if (entity != null)
            getSession().delete(entity);
    }

    @Override
    public void removeItemById(final ID id) {
        if (id != null)
            removeItem(getItemById(id));
    }

}
