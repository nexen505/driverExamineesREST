package com.komarmoss.messaging.model.entity;

import com.komarmoss.model.entity.Identifiable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exdr_changes", schema = "public", catalog = "postgres")
public class ChangesMessageEntity implements Identifiable {

    private Integer id;
    private ChangesTypeEnum type;
    private Date time;
    private String details;
    private String entityClassName;

    public ChangesMessageEntity(Integer id, ChangesTypeEnum type, Date time, String details, String entityClassName) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.details = details;
    }

    public ChangesMessageEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer key) {
        this.id = key;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    public ChangesTypeEnum getType() {
        return type;
    }

    public void setType(ChangesTypeEnum type) {
        this.type = type;
    }

    @Column(name = "time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name = "details", nullable = false)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Column(name = "entity_class_name", nullable = false)
    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangesMessageEntity entity = (ChangesMessageEntity) o;
        return Objects.equals(id, entity.id) &&
                type == entity.type &&
                Objects.equals(time, entity.time) &&
                Objects.equals(details, entity.details) &&
                Objects.equals(entityClassName, entity.entityClassName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, time, details, entityClassName);
    }

    public enum ChangesTypeEnum {
        CREATE, UPDATE, DELETE
    }
}
