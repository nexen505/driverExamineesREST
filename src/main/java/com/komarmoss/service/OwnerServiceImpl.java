package com.komarmoss.service;

import com.komarmoss.messaging.model.vo.ChangesMessageVO;
import com.komarmoss.messaging.service.MessageSender;
import com.komarmoss.model.dao.OwnerDAO;
import com.komarmoss.model.entity.OwnerEntity;
import com.komarmoss.model.vo.OwnerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private final OwnerDAO ownerDAO;

    private final MessageSender messageSender;

    @Autowired
    public OwnerServiceImpl(OwnerDAO ownerDAO, MessageSender messageSender) {
        this.ownerDAO = ownerDAO;
        this.messageSender = messageSender;
    }

    @Override
    public List<OwnerVO> findOwners() {
        final List<OwnerEntity> allItems = ownerDAO.getAllItems();
        allItems.forEach(OwnerEntity::getTransportList); // for lazy initialization
        return allItems
                .parallelStream()
                .map(OwnerVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerVO findOwner(Integer id) {
        return new OwnerVO(ownerDAO.getItemById(id));
    }

    @Override
    public OwnerVO saveOrUpdateOwner(OwnerVO owner) {
        OwnerEntity ownerEntity = owner.createEntity();
        ownerDAO.saveOrUpdateItem(ownerEntity);
        messageSender.send(owner.getId() == null ? ChangesMessageVO.createCreateMessage(owner) : ChangesMessageVO.createUpdateMessage(owner));
        Integer id = ownerEntity.getId();
        return findOwner(id);
    }

    @Override
    public boolean removeOwner(Integer id) {
        if (id != null) {
            ownerDAO.removeItemById(id);
            messageSender.send(ChangesMessageVO.createDeleteMessage(id));
            return true;
        }
        return false;
    }
}
