package com.komarmoss.model.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.entity.Identifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface ValueObject<T extends Identifiable> extends Serializable {
    @NotNull
    T createEntity();

    @Nullable
    default String toJson() {
        try {
            return new CustomObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @NotNull
    default String getEntityClassName() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return ((Class) pt.getActualTypeArguments()[0]).getSimpleName();
    }
}
