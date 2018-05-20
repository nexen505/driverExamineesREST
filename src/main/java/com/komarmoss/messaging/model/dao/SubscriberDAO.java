package com.komarmoss.messaging.model.dao;

import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.messaging.model.entity.SubscriberEntity;
import com.komarmoss.model.dao.AbstractDAO;

import java.util.List;

public interface SubscriberDAO extends AbstractDAO<SubscriberEntity, Integer> {
    List<SubscriberEntity> getSubscribers(ChangesMessageEntity.ChangesTypeEnum type, String entityClassName);
}
