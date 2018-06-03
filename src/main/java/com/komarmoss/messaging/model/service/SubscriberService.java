package com.komarmoss.messaging.model.service;

import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.messaging.model.entity.SubscriberEntity;

import java.util.List;

public interface SubscriberService {
    List<SubscriberEntity> getSubscribers(ChangesMessageEntity.ChangesTypeEnum type, String entityClassName);
}
