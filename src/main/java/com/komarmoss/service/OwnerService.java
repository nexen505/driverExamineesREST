package com.komarmoss.service;

import com.komarmoss.model.vo.OwnerVO;

import java.util.List;

public interface OwnerService {

    List<OwnerVO> findOwners();

    OwnerVO findOwner(Integer id);

    OwnerVO saveOrUpdateOwner(OwnerVO owner);

    boolean removeOwner(Integer id);
}
