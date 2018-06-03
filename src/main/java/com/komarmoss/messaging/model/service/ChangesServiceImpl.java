package com.komarmoss.messaging.model.service;

import com.komarmoss.messaging.model.dao.ChangesDAO;
import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.messaging.model.vo.ChangesMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangesServiceImpl implements ChangesService {

    private final ChangesDAO changesDAO;

    @Autowired
    public ChangesServiceImpl(ChangesDAO changesDAO) {
        this.changesDAO = changesDAO;
    }

    @Override
    public ChangesMessageVO findChange(Integer id) {
        return new ChangesMessageVO(changesDAO.getItemById(id));
    }

    @Override
    public ChangesMessageVO saveOrUpdateChange(ChangesMessageVO msg) {
        final ChangesMessageEntity entity = msg.createEntity();
        changesDAO.saveOrUpdateItem(entity);
        return findChange(entity.getId());
    }
}
