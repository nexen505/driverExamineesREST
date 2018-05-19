package com.komarmoss.messaging.model.dao;

import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.model.dao.AbstractDAOImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ChangesDAOImpl extends AbstractDAOImpl<ChangesMessageEntity, Integer> implements ChangesDAO {
}
