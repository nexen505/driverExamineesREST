package com.komarmoss.messaging.model.vo;

import com.komarmoss.messaging.model.entity.ChangesMessageEntity;
import com.komarmoss.model.vo.ValueObject;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ChangesMessageVO implements ValueObject<ChangesMessageEntity> {

    private Integer id;
    private ChangesMessageEntity.ChangesTypeEnum type;
    private Date time;
    private String details;
    private String changedEntityClass;

    private ChangesMessageVO(ChangesMessageEntity.ChangesTypeEnum type, Date time, String details, String changedEntityClass) {
        this.type = type;
        this.time = time;
        this.details = details;
        this.changedEntityClass = changedEntityClass;
    }

    public ChangesMessageVO(ChangesMessageEntity entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.details = entity.getDetails();
        this.time = entity.getTime();
        this.changedEntityClass = entity.getChangedEntityClassName();
    }

    @NotNull
    public static ChangesMessageVO createDeleteMessage(String entityClass, Serializable id) {
        return new ChangesMessageVO(ChangesMessageEntity.ChangesTypeEnum.DELETE, new Date(), Objects.toString(id), entityClass);
    }

    @NotNull
    public static ChangesMessageVO createCreateMessage(ValueObject obj) {
        return new ChangesMessageVO(ChangesMessageEntity.ChangesTypeEnum.CREATE, new Date(), obj.toJson(), obj.getEntityClassName());
    }

    @NotNull
    public static ChangesMessageVO createUpdateMessage(ValueObject obj) {
        return new ChangesMessageVO(ChangesMessageEntity.ChangesTypeEnum.UPDATE, new Date(), obj.toJson(), obj.getEntityClassName());
    }

    @NotNull
    @Override
    public ChangesMessageEntity createEntity() {
        return new ChangesMessageEntity(this.id, this.type, this.time, this.details, this.changedEntityClass);
    }

    public Integer getId() {
        return id;
    }

    public ChangesMessageEntity.ChangesTypeEnum getType() {
        return type;
    }

    public Date getTime() {
        return time;
    }

    public String getDetails() {
        return details;
    }

    public String getChangedEntityClass() {
        return changedEntityClass;
    }
}
