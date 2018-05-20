package com.komarmoss.messaging.model.entity;

import com.komarmoss.model.entity.Identifiable;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exdr_changes_subscribers", schema = "public", catalog = "postgres")
public class SubscriberEntity implements Identifiable {

    private Integer id;
    private String email;
    private String entityClassName;
    private ChangesMessageEntity.ChangesTypeEnum type;

    public SubscriberEntity() {
    }

    public SubscriberEntity(Integer id, String email, String entityClassName, ChangesMessageEntity.ChangesTypeEnum type) {
        this.id = id;
        this.email = email;
        this.entityClassName = entityClassName;
        this.type = type;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Email
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "entity_class_name", nullable = false)
    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    public ChangesMessageEntity.ChangesTypeEnum getType() {
        return type;
    }

    public void setType(ChangesMessageEntity.ChangesTypeEnum type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriberEntity that = (SubscriberEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(entityClassName, that.entityClassName) &&
                type == that.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, entityClassName, type);
    }
}
