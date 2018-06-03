package com.komarmoss.messaging.model.service;

import com.komarmoss.messaging.model.dao.SubscriberDAO;
import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.messaging.model.entity.SubscriberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberDAO subscriberDAO;

    @Autowired
    public SubscriberServiceImpl(SubscriberDAO subscriberDAO) {
        this.subscriberDAO = subscriberDAO;
    }

    @Override
    public List<SubscriberEntity> getSubscribers(ChangesMessageEntity.ChangesTypeEnum type, String entityClassName) {
        return subscriberDAO.getSubscribers(type, entityClassName);
    }
}
