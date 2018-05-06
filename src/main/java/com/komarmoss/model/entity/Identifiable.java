package com.komarmoss.model.entity;

import java.io.Serializable;

/**
 * Интерфейс для сущности с идентификатором.
 */
public interface Identifiable extends Serializable {
    Serializable getId();
}
