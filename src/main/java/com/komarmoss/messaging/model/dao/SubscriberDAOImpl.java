package com.komarmoss.messaging.model.dao;

import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.messaging.model.entity.SubscriberEntity;
import com.komarmoss.model.dao.AbstractDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class SubscriberDAOImpl extends AbstractDAOImpl<SubscriberEntity, Integer> implements SubscriberDAO {
    @Override
    public List<SubscriberEntity> getSubscribers(ChangesMessageEntity.ChangesTypeEnum type, String entityClassName) {
        final Query query = getSession().createQuery("from SubscriberEntity where type=:type and entityClassName=:entityClassName");
        query.setParameter("type", type);
        query.setString("entityClassName", entityClassName);
        return (List<SubscriberEntity>) query.list();
    }
}
