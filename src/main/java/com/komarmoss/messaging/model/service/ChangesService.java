package com.komarmoss.messaging.model.service;

import com.komarmoss.messaging.model.vo.ChangesMessageVO;

public interface ChangesService {
    ChangesMessageVO findChange(Integer id);

    ChangesMessageVO saveOrUpdateChange(ChangesMessageVO msg);
}
